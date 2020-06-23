package hva.c19.int_bank_of_hva.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.awt.*;
import java.util.List;

@Entity(name = "ParticulierRekening")
public class ParticulierRekening extends Rekening {


    //Constructors
    public ParticulierRekening() {
        this(null, 0);
    }

    public ParticulierRekening(String rekeningnummer, double saldo) {
        super(rekeningnummer, saldo);
    }

}
