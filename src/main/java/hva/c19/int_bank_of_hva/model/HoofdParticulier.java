package hva.c19.int_bank_of_hva.model;

import javax.persistence.Entity;

@Entity
public class HoofdParticulier extends Medewerker {

    //Constructors
    public HoofdParticulier() {
        this(null, null, null, null, null);
    }

    public HoofdParticulier(String voornaam, String tussenvoegsel, String achternaam, String gebruikersnaam, String wachtwoord) {
        super(voornaam, tussenvoegsel, achternaam, gebruikersnaam, wachtwoord);
    }
}
