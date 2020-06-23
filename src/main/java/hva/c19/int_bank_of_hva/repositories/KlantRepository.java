package hva.c19.int_bank_of_hva.repositories;

import hva.c19.int_bank_of_hva.model.Klant;
import hva.c19.int_bank_of_hva.model.ParticulierRekening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface KlantRepository extends JpaRepository<Klant, Integer> {

    Klant findKlantByGebruikersnaam(String gebruikersnaam);

    Klant findKlantByBsn(int bsn);

    Klant findKlantByEmailadres(String email);

    Klant findKlantByGebruikersnaamAndWachtwoord(String gebruikersnaam, String wachtwoord);

    Klant findKlantByVoornaam(String voornaam);


    @Query(value = "SELECT k.klant_nr, k.aanhef, k.voorletters, k.tussenvoegsel, k.achternaam, k.bsn, " +
            "k.emailadres, k.geboortedatum, k.gebruikersnaam, k.huisnummer, k.postcode, k.straat, " +
            "k.toevoeging, k.voornaam, k.wachtwoord, k.woonplaats, k.is_enabled, sum(r.saldo) AS saldo " +
            "FROM klant k JOIN rekening_klants rk ON k.klant_nr = rk.klants_klant_nr " +
            "JOIN rekening r ON rk.rekening_rekening_id = r.rekening_id WHERE dtype = 'ParticulierRekening' " +
            "GROUP BY k.klant_nr ORDER BY saldo DESC LIMIT 10;", nativeQuery = true)
    List<Klant> klantenMetHoogsteSaldoKlant();

}
