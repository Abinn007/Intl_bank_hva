package hva.c19.int_bank_of_hva.utilities;

import hva.c19.int_bank_of_hva.model.Klant;
import hva.c19.int_bank_of_hva.model.Rekening;
import hva.c19.int_bank_of_hva.model.ZakelijkRekening;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;

public class RekeningBackingBean {
    private String rekeningnummer;
    private Klant klant;
    private double saldo;
    private String bedrijfsnaam;
    private int kvkNummer;
    private String btwNummer;
    private String sector;


    public RekeningBackingBean(){super();}

    public RekeningBackingBean(String bedrijfsnaam, int kvkNummer, String btwNummer, String sector) {
        this.bedrijfsnaam = bedrijfsnaam;
        this.kvkNummer = kvkNummer;
        this.btwNummer = btwNummer;
        this.sector = sector;
    }

    public RekeningBackingBean(String rekeningnummer, Klant klant, double saldo, String bedrijfsnaam, int kvkNummer, String btwNummer, String sector) {
        this.rekeningnummer = rekeningnummer;
        this.klant = klant;
        this.saldo = saldo;
        this.bedrijfsnaam = bedrijfsnaam;
        this.kvkNummer = kvkNummer;
        this.btwNummer = btwNummer;
        this.sector = sector;
    }

//    public ZakelijkRekening zakelijkRekening() {
//        ZakelijkRekening zakelijkRekening = new ZakelijkRekening(rekeningnummer, klant, saldo, bedrijfsnaam, kvkNummer, btwNummer, sector);
//        return zakelijkRekening;
//    }


    public String getRekeningnummer() {
        return rekeningnummer;
    }

    public void setRekeningnummer(String rekeningnummer) {
        this.rekeningnummer = rekeningnummer;
    }

    public Klant getKlant() {
        return klant;
    }

    public void setKlant(Klant klant) {
        this.klant = klant;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getBedrijfsnaam() {
        return bedrijfsnaam;
    }

    public void setBedrijfsnaam(String bedrijfsnaam) {
        this.bedrijfsnaam = bedrijfsnaam;
    }

    public int getKvkNummer() {
        return kvkNummer;
    }

    public void setKvkNummer(int kvkNummer) {
        this.kvkNummer = kvkNummer;
    }

    public String getBtwNummer() {
        return btwNummer;
    }

    public void setBtwNummer(String btwNummer) {
        this.btwNummer = btwNummer;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
}
