package hva.c19.int_bank_of_hva.repositories;

import hva.c19.int_bank_of_hva.model.Klant;
import hva.c19.int_bank_of_hva.model.ParticulierRekening;
import hva.c19.int_bank_of_hva.model.Rekening;
import hva.c19.int_bank_of_hva.model.ZakelijkRekening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface ZakelijkRekeningRepository extends JpaRepository<ZakelijkRekening,Integer> {

    List<ZakelijkRekening>findAllByRekeningnummer(String rekeningnummer);

    List<ZakelijkRekening> findAllByKlants(Klant klant);

    ZakelijkRekening findByRekeningnummer(String rekeningNr);


}