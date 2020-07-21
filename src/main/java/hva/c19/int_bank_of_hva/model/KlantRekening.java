package hva.c19.int_bank_of_hva.model;

public interface KlantRekening {

    Integer getKlantNr();
    String getVoorletters();
    String getTussenvoegsel();
    String getAchternaam();
    Double getSaldo();
}
