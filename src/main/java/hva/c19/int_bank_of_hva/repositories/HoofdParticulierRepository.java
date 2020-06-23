package hva.c19.int_bank_of_hva.repositories;

import hva.c19.int_bank_of_hva.model.HoofdParticulier;
import hva.c19.int_bank_of_hva.model.Medewerker;
import hva.c19.int_bank_of_hva.model.ParticulierRekening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoofdParticulierRepository extends JpaRepository<HoofdParticulier, Integer> {

    Medewerker findMedewerkerByGebruikersnaam (String gebruikersnaam);

}
