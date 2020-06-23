package hva.c19.int_bank_of_hva.repositories;

import hva.c19.int_bank_of_hva.model.HoofdParticulier;
import hva.c19.int_bank_of_hva.model.HoofdZakelijk;
import hva.c19.int_bank_of_hva.model.Medewerker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoofdZakelijkRepository extends JpaRepository<HoofdZakelijk, Integer> {

    Medewerker findMedewerkerByGebruikersnaam (String gebruikersnaam);
}
