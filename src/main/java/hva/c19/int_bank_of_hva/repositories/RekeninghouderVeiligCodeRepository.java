package hva.c19.int_bank_of_hva.repositories;

import hva.c19.int_bank_of_hva.model.RekeninghouderVeiligCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RekeninghouderVeiligCodeRepository extends JpaRepository<RekeninghouderVeiligCode, Integer> {

    RekeninghouderVeiligCode findByVeiligCode(int veiligCode);
}
