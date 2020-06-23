package hva.c19.int_bank_of_hva.service;

import hva.c19.int_bank_of_hva.model.Medewerker;
import hva.c19.int_bank_of_hva.repositories.HoofdParticulierRepository;
import org.springframework.stereotype.Service;

@Service
public class HoofdParticulierService {
    private final HoofdParticulierRepository hoofdParticulierRepository;

    public HoofdParticulierService(HoofdParticulierRepository medewerkerRepo) {
        super();
        this.hoofdParticulierRepository = medewerkerRepo;
    }

    public Medewerker getMedewerkerByGebruikersnaam(String gebruikersnaam) {
        return hoofdParticulierRepository.findMedewerkerByGebruikersnaam(gebruikersnaam);
    }

}
