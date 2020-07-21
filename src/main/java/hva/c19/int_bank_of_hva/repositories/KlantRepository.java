package hva.c19.int_bank_of_hva.repositories;

import hva.c19.int_bank_of_hva.model.Klant;
import hva.c19.int_bank_of_hva.model.KlantRekening;
import hva.c19.int_bank_of_hva.model.Rekening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface KlantRepository extends JpaRepository<Klant, Integer> {

    Klant findKlantByGebruikersnaam(String gebruikersnaam);

    Klant findKlantByBsn(int bsn);

    Klant findKlantByEmailadres(String email);


    @Query(value = "SELECT k.klant_nr, k.voorletters, k.tussenvoegsel, k.achternaam, sum(r.saldo) AS saldo " +
            "FROM klant k JOIN rekening_klants rk ON k.klant_nr = rk.klants_klant_nr " +
            "JOIN rekening r ON rk.rekening_rekening_id = r.rekening_id WHERE dtype = 'ParticulierRekening' " +
            "GROUP BY k.klant_nr ORDER BY saldo DESC LIMIT 10;", nativeQuery = true)
    List<KlantRekening> klantenMetHoogsteSaldoKlant();

    @Query(value = "SELECT * FROM klant k \n" +
            "JOIN rekening_klants rk \n" +
            "ON k.klant_nr = rk.klants_klant_nr \n" +
            "WHERE rk.rekening_rekening_id = :rekeningId", nativeQuery = true)
    List<Klant> findAllByRekeningId(@Param("rekeningId") int rekeningId);

    @Query(value = "SELECT * FROM klant k JOIN rekening_klants rk ON rk.klants_klant_nr = k.klant_nr JOIN rekening r \n" +
            "ON r.rekening_id = rk.rekening_rekening_id WHERE r.rekening_id = :rekeningId", nativeQuery = true)
    List<Klant> rekeninghouders(@Param("rekeningId") int rekeningId);


}
