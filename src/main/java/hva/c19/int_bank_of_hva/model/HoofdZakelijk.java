package hva.c19.int_bank_of_hva.model;

import javax.persistence.Entity;


@Entity
public class HoofdZakelijk extends Medewerker {

    //Constructors
    public HoofdZakelijk() {
        this(null, null, null, null, null);
    }

    public HoofdZakelijk(String voornaam, String tussenvoegsel, String achternaam, String gebruikersnaam, String wachtwoord) {
        super(voornaam, tussenvoegsel, achternaam, gebruikersnaam, wachtwoord);
    }
}
