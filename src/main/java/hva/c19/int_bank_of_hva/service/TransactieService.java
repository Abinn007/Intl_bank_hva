package hva.c19.int_bank_of_hva.service;

import hva.c19.int_bank_of_hva.model.Rekening;
import hva.c19.int_bank_of_hva.model.Transactie;
import hva.c19.int_bank_of_hva.repositories.RekeningRepository;
import hva.c19.int_bank_of_hva.repositories.TransactieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.text.SimpleDateFormat;

@Service
public class TransactieService {
    @Autowired
    private final TransactieRepository transactieRepository;

    @Autowired
    private final RekeningRepository rekeningRepository;


    // Constructors
    public TransactieService(){
        this(null,null);
    }

    public TransactieService(TransactieRepository transactieRepository, RekeningRepository rekeningRepository) {
        this.transactieRepository = transactieRepository;
        this.rekeningRepository = rekeningRepository;
    }

    // Methods
    /**
     * Deze methode voert een geldtransactie uit en slaat de transactie op.
     *
     * @Transactional Alles in de body van de methode lukt, of niks.
     *
     * @param transactie Bevat de benodigde, in de view ingevulde, informatie.
     */
    @Transactional
    public void maakGeldOver(Transactie transactie) {
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        transactie.setDatumTransactie(date);

        Rekening rekeningCredit = rekeningRepository.findByRekeningnummer(transactie.getRekeningNrCredit());
        rekeningCredit.setSaldo(rekeningCredit.getSaldo() + transactie.getTransactieBedrag());
        Rekening rekeningDebet = rekeningRepository.findByRekeningnummer(transactie.getRekeningNrDebet());
        rekeningDebet.setSaldo(rekeningDebet.getSaldo() - transactie.getTransactieBedrag());

        saveTransactie(transactie);
    }

    public boolean bestaandRekeningNr(String rekeningnr) {
        return rekeningRepository.findByRekeningnummer(rekeningnr) != null;
    }

    public List<Transactie> transactieList(String rekeningnummer) {
        return transactieRepository.meestRecenteTransactiesPerRekening(rekeningnummer);
    }

    /**
     * Om een transactie op te slaan
     *
     * @param transactie een nieuwe transactie
     */
    public void saveTransactie(Transactie transactie) {
        transactieRepository.save(transactie);
    }

    /**
     * Haalt de totaal debit transacties uit de database.
     *
     * @return lijst debit transacties
     */
    private Map<String, Integer> totaalDebetTransacties() {
        Map<String, Integer> debetTransacties = new HashMap<>();
        for (Object[] transacties : transactieRepository.totaalDebetTransactie()) {
            debetTransacties.put((String) transacties[0], ((Number) transacties[1]).intValue());
        }
        return debetTransacties;
    }

    /**
     * Haalt de totaal credit transacties uit de database.
     *
     * @return lijst credit transacties
     */
    private Map<String, Integer> totaalCreditTransacties() {
        Map<String, Integer> creditTransacties = new HashMap<>();
        for (Object[] transacties : transactieRepository.totaalCreditTransactie()) {
            creditTransacties.put((String) transacties[0], ((Number) transacties[1]).intValue());
        }
        return creditTransacties;
    }

    /**
     * Mergen totaal debet en totaal credit transacties.
     *
     * @return lijst met totaal transacties.
     */
    private Map<String, Integer> totaalTransacties() {
        Map<String, Integer> debetTransacties = totaalDebetTransacties();
        Map<String, Integer> creditTransacties = totaalCreditTransacties();
        Map<String, Integer> totaalTransacties = new HashMap<>(debetTransacties);
        for (String i : creditTransacties.keySet()) {
            if (totaalTransacties.containsKey(i)) {
                totaalTransacties.put(i, creditTransacties.get(i) + totaalTransacties.get(i));
            } else {
                totaalTransacties.put(i, creditTransacties.get(i));
            }
        }
        return totaalTransacties;
    }

    public Transactie getTransactieById(int transactieNr) {
        Optional<Transactie> transactie = transactieRepository.findById(transactieNr);
        return transactie.orElse(null);
    }

    /**
     * Sorteeren van totaal transacties hashmap
     *
     * @return sorted hashmap
     */
    private LinkedHashMap<String, Integer> sortedTransactiesList() {
        Map<String, Integer> transacties = totaalTransacties();
        Set<Map.Entry<String, Integer>> entrySet = transacties.entrySet();
        Comparator<Map.Entry<String, Integer>> valueComparator = entryComparator();
        List<Map.Entry<String, Integer>> entryList = new ArrayList<Map.Entry<String, Integer>>(entrySet);
        entryList.sort(valueComparator);
        LinkedHashMap<String, Integer> sortedByValue = new LinkedHashMap<String, Integer>(entryList.size());
        for (Map.Entry<String, Integer> entry : entryList) {
            sortedByValue.put(entry.getKey(), entry.getValue());
        }
        return sortedByValue;
    }

    /**
     * Entry comparator om values te sorteren in reverseorder
     *
     * @return
     */
    private Comparator<Map.Entry<String, Integer>> entryComparator() {
        return (e1, e2) -> {
            Integer v1 = e1.getValue();
            Integer v2 = e2.getValue();
            return v2.compareTo(v1);
        };
    }

    /**
     * Om een aantal hoogste transacties te laten zien op een willekeurige aantal.
     *
     * @param aantal willekeurige nummer
     * @return transactie lijst
     */
    public Map<String, Integer> aantalHoogsteTransacties(int aantal) {
        LinkedHashMap<String, Integer> totaalSortedTransacties = sortedTransactiesList();
        Map<String, Integer> aantalTransacties = new LinkedHashMap<>();
        Set<String> keys = totaalSortedTransacties.keySet();
        String[] keysArray = keys.toArray(new String[0]);
        Collection<Integer> values = totaalSortedTransacties.values();
        Integer[] valuesArray = values.toArray(new Integer[0]);
        for (int i = 0; i < aantal && i < keysArray.length; i++) {
            aantalTransacties.put(keysArray[i], valuesArray[i]);
        }
        return aantalTransacties;
    }
}


