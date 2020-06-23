package hva.c19.int_bank_of_hva.repositories;

import hva.c19.int_bank_of_hva.model.ParticulierRekening;
import hva.c19.int_bank_of_hva.model.Rekening;
import hva.c19.int_bank_of_hva.model.ZakelijkRekening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface RekeningRepository extends JpaRepository<Rekening, Integer> {

    @Query(value = "select max(rekeningnummer) from rekening  where dtype = 'ParticulierRekening' ", nativeQuery = true)
    String hoogsteParticulierRekeningnummer();

    @Query(value = "select max(rekeningnummer) from rekening  where dtype = 'ZakelijkRekening' ", nativeQuery = true)
    String hoogsteZakelijkRekeningnummer();

    @Query(value = "SELECT rekening_id, rekeningnummer, SUM(saldo) AS saldo, \n" +
            "bedrijfsnaam, btw_nummer, kvk_nummer, sector \n" +
            "FROM rekening WHERE dtype = 'ZakelijkRekening' \n" +
            "GROUP BY bedrijfsnaam ORDER BY saldo DESC LIMIT 10;", nativeQuery = true)
    List<ZakelijkRekening> bedrijvenMetHoogsteSaldo();

    @Query(value = "SELECT rekening_id, rekeningnummer,round(AVG(saldo),2) AS saldo, \n" +
            "bedrijfsnaam, btw_nummer, kvk_nummer, sector \n" +
            "FROM rekening WHERE dtype = 'ZakelijkRekening'\n" +
            "GROUP BY sector ORDER BY sector ASC;", nativeQuery = true)
    List<ZakelijkRekening> gemiddeldeSaldoPerSector();

    @Query(value = "SELECT rekening_id, rekeningnummer,saldo, \n" +
            "bedrijfsnaam, btw_nummer, kvk_nummer, sector \n" +
            "FROM rekening WHERE dtype = 'ZakelijkRekening'\n", nativeQuery = true)
    List<ZakelijkRekening> alleZakelijkRekeningen();

    @Query(value="select * from rekening  where bedrijfsnaam = :bedrijfsnaam", nativeQuery = true )
    List<Rekening> findByBedrijfsnaam (@Param("bedrijfsnaam") String bederijfsnaam);

    @Modifying
    @Query(value = "UPDATE rekening r \n" +
            "SET r.saldo = r.saldo - :bedrag, \n" +
            "WHERE r.rekeningnummer = :rekeningnr ;", nativeQuery = true)
    int maakGeldOverVan(
            @Param("bedrag") double bedrag,
            @Param("rekeningnr") String rekeningnr);

    @Modifying
    @Query(value = "UPDATE rekening r \n" +
            "SET r.saldo = r.saldo + :bedrag \n" +
            "WHERE r.rekeningnummer = :rekeningnr ;", nativeQuery = true)
    int maakGeldOverNaar(
            @Param("bedrag") double bedrag,
            @Param("rekeningnr") String rekeningnr);

    Rekening findByRekeningnummer(String rekeningnummer);

    @Query(value = "SELECT * FROM rekening r JOIN rekening_klants rk ON rk.rekening_rekening_id = r.rekening_id " +
            "JOIN klant k ON rk.klants_klant_nr = k.klant_nr WHERE k.gebruikersnaam = :gebruikersnaam " +
            "AND dtype = 'ParticulierRekening';", nativeQuery = true)
    List<Rekening> particulierRekeningen(@Param("gebruikersnaam") String gebruikersnaam);

    @Query(value = "SELECT * FROM rekening r JOIN rekening_klants rk ON rk.rekening_rekening_id = r.rekening_id " +
            "JOIN klant k ON rk.klants_klant_nr = k.klant_nr WHERE k.gebruikersnaam = :gebruikersnaam " +
            "AND dtype = 'ZakelijkRekening';", nativeQuery = true)
    List<Rekening> zakelijkRekeningen(@Param("gebruikersnaam") String gebruikersnaam);

    @Query(value = "SELECT r.rekening_id, rekeningnummer, SUM(r.saldo) AS saldo, " +
            "k.klant_nr, k.aanhef, k.voorletters, k.tussenvoegsel, k.achternaam " +
        "FROM rekening r JOIN rekening_klants rk ON r.rekening_id = rk.rekening_rekening_id " +
        "JOIN klant k ON rk.klants_klant_nr = k.klant_nr WHERE dtype = 'ParticulierRekening' " +
        "GROUP BY k.klant_nr ORDER BY saldo DESC LIMIT 10;", nativeQuery = true)
    List<ParticulierRekening> klantenMetHoogsteSaldo();

}
