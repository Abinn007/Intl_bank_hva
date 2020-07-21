package hva.c19.int_bank_of_hva.service;

import hva.c19.int_bank_of_hva.model.Klant;
import hva.c19.int_bank_of_hva.model.KlantRekening;
import hva.c19.int_bank_of_hva.repositories.KlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class KlantService {

    @Autowired
    private final KlantRepository klantRepo;

    //Constructors
    public KlantService() {
        this(null);
    }

    public KlantService(KlantRepository klantRepo) {
        super();
        this.klantRepo = klantRepo;
    }

    /**
     * Om een klant op te slaan in de database
     *
     * @param klant nieuwe klant
     */
    public void saveKlant(Klant klant) {
        klantRepo.save(klant);
    }

    /**
     * Checken in de database of een bsn met een bestaande klant hoort.
     *
     * @param bsn bsn nummer ingevoerd door de nieuwe klant.
     * @return true als de bsn in de database al bestaat.
     */
    public boolean isBestaandeBsn(String bsn) {
        int nr = Integer.parseInt(bsn);
        return klantRepo.findKlantByBsn(nr) != null;
    }

    /**
     * Checken in de database of een gebruikersnaam met een bestaande klant hoort.
     *
     * @param gebruikersnaam ingevoerd door de nieuwe klant.
     * @return true als de gebruikersnaam in de database al bestaat.
     */
    public boolean isBestaandeGebruikersnaam(String gebruikersnaam) {
        return klantRepo.findKlantByGebruikersnaam(gebruikersnaam) != null;
    }

    /**
     * Checken in de database of een emailadres met een bestaande klant hoort.
     *
     * @param emailadres ingevoerd door de nieuwe klant.
     * @return true als de emailadres in de database al bestaat
     */
    public boolean isBestaandeEmailadres(String emailadres) {
        return klantRepo.findKlantByEmailadres(emailadres) != null;
    }

    /**
     * Haalt een klant met een gebruikersnaam uit de database.
     *
     * @param gebruikersnaam gebruikersnaam.
     * @return Klant.
     */
    public Klant findKlantByGebruikersnaam(String gebruikersnaam) {
        return klantRepo.findKlantByGebruikersnaam(gebruikersnaam);

    }

    public void updateKlant(Klant klant) {
        klantRepo.save(klant);
    }

    public List<KlantRekening> klantenMetHoogsteParticulierRekeningSaldoKlant() {
        return klantRepo.klantenMetHoogsteSaldoKlant();
    }

    public List<Klant> findAllByRekeningId(int id) {
        return klantRepo.findAllByRekeningId(id);
    }

    public List<Klant> rekeninghouders(int rekeningId) {return klantRepo.rekeninghouders(rekeningId);}

    /**
     * Checken in de database of een bsn met een bestaande klant hoort en
     * die niet hetzelfde als nu is.
     *
     * @param bsn nieuwe bsn nummer.
     * @param klant ingelogd klant.
     * @return true als de bsn ongeldig is.
     */
    public boolean isOngeldigBsn(String bsn, Klant klant) {
        int nwBsn = Integer.parseInt(bsn);
        return klantRepo.findKlantByBsn(nwBsn) != klant;
    }

    /**
     * Checken in de database of een emailadres met een bestaande klant hoort en
     * die niet hetzelfde als nu is.
     *
     * @param emailadres nieuwe emailadres.
     * @param klant ingelogd klant.
     * @return true als het emailadres ongeldig is.
     */
    public boolean isOngeldigEmailadres(String emailadres, Klant klant) {
        return klantRepo.findKlantByEmailadres(emailadres) != null &&
                klantRepo.findKlantByEmailadres(emailadres) != klant;
    }

    /**
     * Checken in de database of een gebruikersnaam met een bestaande klant hoort en
     * die niet hetzelfde als nu is.
     *
     * @param gebruikersnaam nieuwe gebruikersnaam.
     * @param klant ingelogd klant.
     * @return true als de gebruikersnaam ongeldig is.
     */
    public boolean isOngeldigGebruikersnaam(String gebruikersnaam, Klant klant) {
        return klantRepo.findKlantByGebruikersnaam(gebruikersnaam) != null &&
                klantRepo.findKlantByGebruikersnaam(gebruikersnaam) != klant;
    }
}
