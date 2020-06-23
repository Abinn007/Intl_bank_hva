package hva.c19.int_bank_of_hva.repositories;
import hva.c19.int_bank_of_hva.model.Rekening;
import hva.c19.int_bank_of_hva.model.Transactie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface TransactieRepository extends JpaRepository<Transactie, Integer> {
    List<Transactie> findAllByRekeningNrCredit(String RekeningNrCredit);
    List<Transactie> findAllByRekeningNrDebet(String RekeningNrDebet);
    List<Transactie> findAllByRekening(Rekening rekening);

    @Query(value = "SELECT  r.bedrijfsnaam, COUNT(rekening_nr_debet) AS total \n" +
            "FROM transactie tr \n" +
            "JOIN rekening r \n" +
            "ON r.rekeningnummer = tr.rekening_nr_debet \n" +
            "GROUP BY bedrijfsnaam\n" +
            "HAVING bedrijfsnaam IS NOT NULL", nativeQuery = true)
            List<Object[]> totaalDebitTransactie();


    @Query(value = "SELECT  r.bedrijfsnaam, COUNT(rekening_nr_credit) AS total \n" +
            "FROM transactie tr \n" +
            "JOIN rekening r \n" +
            "ON r.rekeningnummer = tr.rekening_nr_credit \n" +
            "GROUP BY bedrijfsnaam\n" +
            "HAVING bedrijfsnaam IS NOT NULL;\n", nativeQuery = true)
            List<Object[]> totaalCreditTransactie();

    @Query (value = "SELECT * FROM intl_bank_of_hva.transactie\n" +
            "WHERE rekening_nr_credit = :rekeningnummer OR rekening_nr_debet = :rekeningnummer\n" +
            "ORDER BY datum_transactie DESC LIMIT 10;", nativeQuery = true)
            List<Transactie> meestRecenteTransactiesPerRekening(@Param("rekeningnummer") String rekeningnummer);
}
