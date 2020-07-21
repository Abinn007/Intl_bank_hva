package hva.c19.int_bank_of_hva.service;

import hva.c19.int_bank_of_hva.model.Klant;
import hva.c19.int_bank_of_hva.repositories.KlantRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class KlantServiceTest {

    @MockBean
    KlantRepository klantRepository = Mockito.mock(KlantRepository.class);

    KlantService klantService = new KlantService(klantRepository);

    public KlantServiceTest() {
        super();
    }

    @Test
    public void getKlantByGebruikersnaamTest() {
        String aanhef = "de heer";
        String voorletters = "H.R.";
        String voornaam = "Hari";
        String tussenvoegsel = "Ram";
        String achternaam = "Gautam";
        String straat = "Ecuplein";
        int huisnummer = 80;
        String toevoeging = "2";
        String postcode = "1061AB";
        String woonplaats = "Amsterdam";
        int bsn = 123456789;
        String geboortedatum = "20-10-2000";
        String emailadres = "harithapa@hotmail.com";
        String gebruikersnaam = "cursist";
        String wachtwoord = "Cohort2019";
        Klant klant = new Klant(aanhef, voorletters, voornaam, tussenvoegsel, achternaam, straat, huisnummer,
                toevoeging, postcode, woonplaats, bsn, geboortedatum, emailadres, gebruikersnaam, wachtwoord);
        Mockito.when(klantService.findKlantByGebruikersnaam(klant.getGebruikersnaam())).thenReturn(klant);
        Klant actual = klantService.findKlantByGebruikersnaam("cursist");
        assertEquals(klant, actual);

    }

}
