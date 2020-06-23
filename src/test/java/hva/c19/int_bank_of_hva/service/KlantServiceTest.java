package hva.c19.int_bank_of_hva.service;

import hva.c19.int_bank_of_hva.model.Klant;
import hva.c19.int_bank_of_hva.repositories.KlantRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@RunWith(SpringRunner.class)
public class KlantServiceTest {


    @TestConfiguration
    static class KlantServiceTestConfiguration {

        @Bean
        public KlantService klantService() {
            return new KlantService();
        }

//        @Bean
//        public KlantRepository klantRepository() {
//            return new KlantRepository();
//        }
    }


    @Autowired
    KlantService klantService;

    @MockBean
    KlantRepository klantRepository;

//    @Before
//    public void testSetup(){
//        String aanhef = "de heer";
//        String voorletters = "H.R.";
//        String voornaam = "Hari";
//        String tussenvoegsel = "Ram";
//        String achternaam = "Gautam" ;
//        String straat = "Ecuplein";
//        int huisnummer = 80;
//        String toevoeging = "2";
//        String postcode = "1061AB";
//        String woonplaats = "Amsterdam";
//        int bsn = 123456789;
//        String geboortedatum = "20-10-2000";
//        String emailadres = "harithapa@hotmail.com";
//        String gebruikersnaam = "cursist";
//        String wachtwoord = "Cohort2019";
//        Klant klant = new Klant(aanhef,voorletters,voornaam,tussenvoegsel,achternaam,straat,huisnummer,toevoeging,
//                                postcode,woonplaats,bsn,geboortedatum,emailadres,gebruikersnaam,wachtwoord);
//
//        klantRepository.save(klant);
//    }

    @Test
    public void getKlantTest(){
        String aanhef = "de heer";
        String voorletters = "H.R.";
        String voornaam = "Hari";
        String tussenvoegsel = "Ram";
        String achternaam = "Gautam" ;
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
        Klant klant = new Klant(aanhef,voorletters,voornaam,tussenvoegsel,achternaam,straat,huisnummer,toevoeging,
                postcode,woonplaats,bsn,geboortedatum,emailadres,gebruikersnaam,wachtwoord);
        klantRepository.save(klant);
        Mockito.when(klantRepository.findKlantByGebruikersnaam("cursgrtist")).thenReturn(klant);
    }

}
