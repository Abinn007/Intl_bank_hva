package hva.c19.int_bank_of_hva.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Klant")
public class Klant {
    @Id
    @GeneratedValue
    private int klantNr;
    private String aanhef;
    private String voorletters;
    private String voornaam;
    private String tussenvoegsel;
    private String achternaam;
    private String straat;
    private int huisnummer;
    private String toevoeging;
    private String postcode;
    private String woonplaats;
    private int bsn;
    private String geboortedatum;
    private String emailadres;
    private String gebruikersnaam;
    private String wachtwoord;


    private boolean isEnabled;

    @ManyToMany(mappedBy = "klants", cascade = {CascadeType.ALL})
    private List<ParticulierRekening> particulierRekeningen;

    @ManyToMany(mappedBy = "klants", cascade = {CascadeType.ALL})
    private List<ZakelijkRekening> zakelijkRekeningen;

    //Constructors
    public Klant(String aanhef, String voorletters, String voornaam, String tussenvoegsel, String achternaam,
                 String straat, int huisnummer, String toevoeging, String postcode, String woonplaats,
                 int bsn, String geboortedatum, String emailadres, String gebruikersnaam, String wachtwoord) {
        this.aanhef = aanhef;
        this.voorletters = voorletters;
        this.voornaam = voornaam;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.toevoeging = toevoeging;
        this.postcode = postcode;
        this.woonplaats = woonplaats;
        this.bsn = bsn;
        this.geboortedatum = geboortedatum;
        this.emailadres = emailadres;
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
    }

    public Klant(int klantNr, String aanhef, String voorletters, String voornaam, String tussenvoegsel, String achternaam,
                 String straat, int huisnummer, String toevoeging, String postcode, String woonplaats,
                 int bsn, String geboortedatum, String emailadres, String gebruikersnaam, String wachtwoord) {
        this.aanhef = aanhef;
        this.voorletters = voorletters;
        this.voornaam = voornaam;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.toevoeging = toevoeging;
        this.postcode = postcode;
        this.woonplaats = woonplaats;
        this.bsn = bsn;
        this.geboortedatum = geboortedatum;
        this.emailadres = emailadres;
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
        this.klantNr = klantNr;
    }

    public Klant(String gebruikersnaam, String wachtwoord) {
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
    }

    public Klant() {
        super();
    }

    public Klant(String aanhef, String voorletters, String achternaam, int bsn) {
        this.aanhef = aanhef;
        this.voorletters = voorletters;
        this.achternaam = achternaam;
        this.bsn = bsn;
    }

    //GETTERS

    public int getKlantNr() {
        return klantNr;
    }

    public String getAanhef() {
        return aanhef;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public String getStraat() {
        return straat;
    }

    public int getHuisnummer() {
        return huisnummer;
    }

    public String getToevoeging() {
        return toevoeging;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public int getBsn() {
        return bsn;
    }

    public String getGeboortedatum() {
        return geboortedatum;
    }

    public String getEmailadres() {
        return emailadres;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    //SETTERS

    public void setKlantNr(int klantNr) {
        this.klantNr = klantNr;
    }

    public void setAanhef(String aanhef) {
        this.aanhef = aanhef;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public void setHuisnummer(int huisnummer) {
        this.huisnummer = huisnummer;
    }

    public void setToevoeging(String toevoeging) {
        this.toevoeging = toevoeging;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public void setBsn(int bsn) {
        this.bsn = bsn;
    }

    public void setGeboortedatum(String geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public void setEmailadres(String emailadres) {
        this.emailadres = emailadres;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public String toString() {
        return "Voorletters: " + this.voorletters + "\n" + "Achternaam: " + this.achternaam + "\n" + "BSN: " + this.bsn + "\n" + "Adres: " +
                this.straat + " " + this.huisnummer + " " + this.postcode + " " + this.woonplaats;
    }

}
