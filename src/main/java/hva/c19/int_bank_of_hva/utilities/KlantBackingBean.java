package hva.c19.int_bank_of_hva.utilities;

import hva.c19.int_bank_of_hva.model.Klant;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class KlantBackingBean {
    private static final String SALT = "QxLUF1bgIAdeQX";
    private int klantNr;
    private String aanhef;
    private String voorletters;
    private String voornaam;
    private String tussenvoegsel;
    private String achternaam;
    private String straat;
    private String huisnummer;
    private String toevoeging;
    private String postcode;
    private String woonplaats;
    private String bsn;
    private String geboortedatum;
    private String emailadres;
    private String gebruikersnaam;
    private String wachtwoord;


    //Default constructor
    public KlantBackingBean() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }

    //All args constructor
    public KlantBackingBean(String aanhef, String voorletters, String voornaam, String tussenvoegsel, String achternaam,
                            String straat, String huisnummer, String toevoeging, String postcode, String woonplaats,
                            String bsn, String geboortedatum, String emailadres, String gebruikersnaam, String wachtwoord) {
       this(0,aanhef,voorletters,voornaam,tussenvoegsel,achternaam,straat,huisnummer,
               toevoeging,postcode,woonplaats,bsn,geboortedatum,emailadres,gebruikersnaam,wachtwoord);
        //this.aanhef = aanhef;
//        this.voorletters = voorletters;
//        this.voornaam = voornaam;
//        this.tussenvoegsel = tussenvoegsel;
//        this.achternaam = achternaam;
//        this.straat = straat;
//        this.huisnummer = huisnummer;
//        this.toevoeging = toevoeging;
//        this.postcode = postcode;
//        this.woonplaats = woonplaats;
//        this.bsn = bsn;
//        this.geboortedatum = geboortedatum;
//        this.emailadres = emailadres;
//        this.gebruikersnaam = gebruikersnaam;
//        this.wachtwoord = wachtwoord;
    }

    public KlantBackingBean(int klantNr, String aanhef, String voorletters, String voornaam, String tussenvoegsel,
                            String achternaam, String straat, String huisnummer, String toevoeging, String postcode,
                            String woonplaats, String bsn, String geboortedatum, String emailadres,
                            String gebruikersnaam, String wachtwoord) {
        this.klantNr = klantNr;
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

    /**
     * Maakt een nieuwe klant van de ingevoerde gegevens
     *
     * @return klant
     */
    public Klant klant() {
        int huisNr = Integer.parseInt(huisnummer);
        int BSN = Integer.parseInt(bsn);
        return new Klant(aanhef, voorletters, voornaam, tussenvoegsel, achternaam, straat, huisNr, toevoeging,
                postcode, woonplaats, BSN, geboortedatum, emailadres, gebruikersnaam, wachtwoord);
    }

    public Klant updateKlant() {
        int huisNr = Integer.parseInt(huisnummer);
        int BSN = Integer.parseInt(bsn);
        return new Klant(klantNr,aanhef, voorletters, voornaam, tussenvoegsel, achternaam, straat, huisNr,
                toevoeging,postcode, woonplaats, BSN, geboortedatum, emailadres, gebruikersnaam, wachtwoord);
    }

    //Getters en Setters


    /**
     * Om de voorletters hoofdletter te maken voordat in de database wordt opgeslagen.
     *
     * @param voorletters
     */
    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters.toUpperCase();
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getVoornaam() {
        return voornaam;
    }

    /**
     * Om de eerste letter van voornaam hoofdletter te maken voordat in de database wordt opgeslagen.
     *
     * @param voornaam
     */
    public void setVoornaam(String voornaam) {
        this.voornaam = (voornaam.substring(0, 1).toUpperCase() + voornaam.substring(1).toLowerCase());
    }

    public String getAchternaam() {
        return achternaam;
    }

    /**
     * Om de eerste letter van achternaam hoofdletter te maken voordat in de database wordt opgeslagen.
     *
     * @param achternaam
     */
    public void setAchternaam(String achternaam) {
        this.achternaam = (achternaam.substring(0, 1).toUpperCase() + achternaam.substring(1).toLowerCase());
    }

    /**
     * Om de letters van de postcode hoofdletter te maken voordat in de database wordt opgeslagen.
     *
     * @param postcode
     */

    public void setPostcode(String postcode) {
        this.postcode = postcode.toUpperCase();
    }

    public String getBsn() {
        return bsn;
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

        this.wachtwoord = gehashedWachtwoord(wachtwoord, SALT);
    }

    public String getEmailadres() {
        return emailadres;
    }

    public String getAanhef() {
        return aanhef;
    }

    public void setAanhef(String aanhef) {
        this.aanhef = aanhef;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public String getToevoeging() {
        return toevoeging;
    }

    public void setToevoeging(String toevoeging) {
        this.toevoeging = toevoeging;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public void setBsn(String bsn) {
        this.bsn = bsn;
    }

    public String getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(String geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public void setEmailadres(String emailadres) {
        this.emailadres = emailadres;
    }

    public int getKlantNr() {
        return klantNr;
    }

    public void setKlantNr(int klantNr) {
        klantNr = klantNr;
    }

    /**
     * Om een gehashed wachtwoord met SALT te genereren van de ingevoerde wachtwoord.
     *
     * @param wachtwoord
     * @param salt
     * @return
     */
    public String gehashedWachtwoord(String wachtwoord, String salt) {
        String hashedWachtwoord = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(wachtwoord.getBytes(StandardCharsets.UTF_8));
            hashedWachtwoord = getString(hashedWachtwoord, bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashedWachtwoord;
    }

    private String getString(String hashedWachtwoord, byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }
        hashedWachtwoord = sb.toString();
        return hashedWachtwoord;
    }
}
