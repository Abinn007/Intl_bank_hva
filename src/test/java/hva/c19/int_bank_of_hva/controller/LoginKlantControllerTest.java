package hva.c19.int_bank_of_hva.controller;

import hva.c19.int_bank_of_hva.model.Klant;
import hva.c19.int_bank_of_hva.repositories.KlantRepository;
import hva.c19.int_bank_of_hva.service.KlantService;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class LoginKlantControllerTest {

    private KlantRepository mockKlantRepository = Mockito.mock(KlantRepository.class);



    @Test
    void handleKlantInloggen() {
//        Klant klant = new Klant();
//        Mockito.when(mockKlantRepository.findKlantByGebruikersnaamAndWachtwoord("emr", "ret.123")).thenReturn(klant);
    }
}