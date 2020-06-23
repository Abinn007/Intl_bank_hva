package hva.c19.int_bank_of_hva.model;

import javax.persistence.*;

@MappedSuperclass
public class Medewerker {
    @Id
    @GeneratedValue
    private int medewerkerNr;

    private String voornaam;
    private String tussenvoegsel;
    private String achternaam;
    private String gebruikersnaam;
    private String wachtwoord;


    //Constructors
    public Medewerker() {
        this(null, null, null, null, null);
    }

    public Medewerker(String voornaam, String tussenvoegsel, String achternaam, String gebruikersnaam, String wachtwoord) {
        this.voornaam = voornaam;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
    }

    //Getters & Setters


    public int getMedewerkerNr() {
        return medewerkerNr;
    }

    public void setMedewerkerNr(int medewerkerNr) {
        this.medewerkerNr = medewerkerNr;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public String toString() {
        return String.format("Naam: %s %s %s", voornaam, tussenvoegsel, achternaam);
    }
}
