package hva.c19.int_bank_of_hva.model;

import javax.persistence.*;

@Entity
public class RekeninghouderVeiligCode {
    @Id
    @GeneratedValue
    private int id;

    private int veiligCode;

    @OneToOne
    private Klant klant;
    @OneToOne
    private Rekening rekening;

    // CONSTRUCTORS
    public RekeninghouderVeiligCode() {
        this(0, null, null);
    }

    public RekeninghouderVeiligCode(int veiligCode, Klant klant, Rekening rekening) {
        this.veiligCode = veiligCode;
        this.klant = klant;
        this.rekening = rekening;
    }

    // SETTERS - GETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVeiligCode() {
        return veiligCode;
    }

    public void setVeiligCode(int veiligCode) {
        this.veiligCode = veiligCode;
    }
}
