package hva.c19.int_bank_of_hva.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Rekening")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Rekening {
    @Id
    @GeneratedValue
    private int rekeningId;

    private String rekeningnummer;
    private double saldo;

    @ManyToMany
    private final List<Klant> klants;

    @OneToMany(mappedBy = "rekening", cascade = {CascadeType.REMOVE})
    private final List<Transactie> transactiehistorie;


    //Constructors
    public Rekening() {
        this(null, 0);
    }

    public Rekening(String rekeningnummer, double saldo) {
        this.rekeningnummer = rekeningnummer;
        this.saldo = saldo;
        klants = new ArrayList<>();
        transactiehistorie = new ArrayList<>();
    }

    //Getters & Setters

    public int getRekeningId() {
        return rekeningId;
    }

    public void setRekeningId(int rekeningId) {
        this.rekeningId = rekeningId;
    }

    public String getRekeningnummer() {
        return rekeningnummer;
    }

    public void setRekeningnummer(String rekeningnummer) {
        this.rekeningnummer = rekeningnummer;
    }

    public void setSaldo(double saldo) {
        this.saldo = round(saldo, 2);
    }

    public double getSaldo() {
        return saldo;
    }

    /**
     * om een klant toe te voegen
     *
     * @param klant
     */
    public void klantToevoegen(Klant klant) {
        klants.add(klant);
    }

    // BigDecimal rounding helper method
    private double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    @Override
    public String toString() {
        return klants + "\nRekeningnummer: " + this.getRekeningnummer() + "\n" + "Saldo: " + this.getSaldo();
    }
}