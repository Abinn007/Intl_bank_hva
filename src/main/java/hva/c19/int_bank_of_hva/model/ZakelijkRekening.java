package hva.c19.int_bank_of_hva.model;

import javax.persistence.*;
import java.util.List;


@Entity(name = "ZakelijkRekening")
public class ZakelijkRekening extends Rekening {

    private String bedrijfsnaam;
    private int kvkNummer;
    private String btwNummer;
    private String sector;


    //Constructors
    public ZakelijkRekening() {
        this(null, 0, null, null);
    }

    public ZakelijkRekening(String bedrijfsnaam, int kvkNummer, String btwNummer, String sector) {
        this.bedrijfsnaam = bedrijfsnaam;
        this.kvkNummer = kvkNummer;
        this.btwNummer = btwNummer;
        this.sector = sector;
    }

    public ZakelijkRekening(String rekeningnummer, double saldo, String bedrijfsnaam, int kvkNummer, String btwNummer, String sector) {
        super(rekeningnummer, saldo);
        this.bedrijfsnaam = bedrijfsnaam;
        this.kvkNummer = kvkNummer;
        this.btwNummer = btwNummer;
        this.sector = sector;
    }


    //Getters & Setters
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

    @Override
    public String toString() {
        return String.format("Rekeningnummer:%s Saldo:%.2f \nBedrijfsnaam:%s KvKnummer:%d BTWnummer:%s Sector:%s",
                getRekeningnummer(), getSaldo(), bedrijfsnaam, kvkNummer, btwNummer, sector);
    }

}



