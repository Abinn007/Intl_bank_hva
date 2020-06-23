package hva.c19.int_bank_of_hva.model;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="token_id")
    private long tokenid;

    @Column(name="confirmation_token")
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne(targetEntity = Klant.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "klant_nr")
    private Klant klant;

    public ConfirmationToken() {
    }

    public ConfirmationToken(Klant klant) {
        this.klant = klant;
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }

    public long getTokenid() {
        return tokenid;
    }

    public void setTokenid(long tokenid) {
        this.tokenid = tokenid;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Klant getKlant() {
        return klant;
    }

    public void setKlant(Klant klant) {
        this.klant = klant;
    }
}
