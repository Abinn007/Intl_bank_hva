package hva.c19.int_bank_of_hva.model;

import javax.persistence.*;

@Entity(name = "Transactie")
public class Transactie {
    @Id
    @GeneratedValue
    private int transactieNr;

    private String rekeningNrDebet;
    private String rekeningNrCredit;
    private double transactieBedrag;
    private String beschrijving;
    private String datumTransactie;

    @ManyToOne
    private Rekening rekening;


    // Constructors
    public Transactie() {this(null, null, 0, null, null,null);
    }

    public Transactie(String rekeningNrDebet, String rekeningNrCredit, double transactieBedrag, String beschrijving, String datumTransactie, Rekening rekening) {
        this.rekeningNrDebet = rekeningNrDebet;
        this.rekeningNrCredit = rekeningNrCredit;
        this.transactieBedrag = transactieBedrag;
        this.beschrijving = beschrijving;
        this.datumTransactie = datumTransactie;
        this.rekening = rekening;
    }

    public int getTransactieNr() {
        return transactieNr;
    }

    public void setTransactieNr(int transactieNr) {
        this.transactieNr = transactieNr;
    }

    public String getRekeningNrDebet() {
        return rekeningNrDebet;
    }

    public void setRekeningNrDebet(String rekeningNrDebet) {
        this.rekeningNrDebet = rekeningNrDebet;
    }

    public String getRekeningNrCredit() {
        return rekeningNrCredit;
    }

    public void setRekeningNrCredit(String rekeningNrCredit) {
        this.rekeningNrCredit = rekeningNrCredit;
    }

    public double getTransactieBedrag() {
        return transactieBedrag;
    }

    public void setTransactieBedrag(double transactieBedrag) {
        this.transactieBedrag = transactieBedrag;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public String getDatumTransactie() {
        return datumTransactie;
    }

    public void setDatumTransactie(String datumTransactie) {
        this.datumTransactie = datumTransactie;
    }


    public Rekening getRekening() {
        return rekening;
    }

    public void setRekening(Rekening rekening) {
        this.rekening = rekening;
    }

    @Override
    public String toString() {
        return "Transactie{" +
                "transactieNr=" + transactieNr +
                ", rekeningNrDebet='" + rekeningNrDebet + '\'' +
                ", rekeningNrCredit='" + rekeningNrCredit + '\'' +
                ", transactieBedrag=" + transactieBedrag +
                ", beschrijving='" + beschrijving + '\'' +
                ", datumTransactie='" + datumTransactie + '\'' +
                '}';
    }
}
