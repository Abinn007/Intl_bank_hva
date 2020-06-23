package hva.c19.int_bank_of_hva.service;

import hva.c19.int_bank_of_hva.model.Medewerker;
import hva.c19.int_bank_of_hva.repositories.HoofdZakelijkRepository;
import org.springframework.stereotype.Service;

@Service
public class HoofdZakelijkService {
    private final HoofdZakelijkRepository hoofdZakelijkRepository;

    public HoofdZakelijkService(HoofdZakelijkRepository hoofdZakelijkRepository) {
        super();
        this.hoofdZakelijkRepository = hoofdZakelijkRepository;
    }

    public Medewerker getMedewerkerByGebruikersnaam(String gebruikersnaam) {
        return hoofdZakelijkRepository.findMedewerkerByGebruikersnaam(gebruikersnaam);
    }
}
