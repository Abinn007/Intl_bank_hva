package hva.c19.int_bank_of_hva.service;

import hva.c19.int_bank_of_hva.model.Klant;
import hva.c19.int_bank_of_hva.repositories.KlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


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
     * Haal alle klanten uit database.
     *
     * @return List<Klant>.
     */
    public List<Klant> getAllKlant() {
        return new ArrayList<>(klantRepo.findAll());
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

    public List<Klant> klantenMetHoogsteParticulierRekeningSaldoKlant() {
        return klantRepo.klantenMetHoogsteSaldoKlant();
    }

}
