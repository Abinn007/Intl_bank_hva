package hva.c19.int_bank_of_hva.repositories;

import hva.c19.int_bank_of_hva.model.RekeninghouderVeiligCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RekeninghouderVeiligCodeRepository extends JpaRepository<RekeninghouderVeiligCode, Integer> {

    @Query(value = "SELECT * FROM intl_bank_of_hva.rekeninghouder_veilig_code WHERE rekening_rekening_id =:rekeningId ", nativeQuery = true)
    RekeninghouderVeiligCode findVeiligCode(@Param("rekeningId") int rekeningId);
}
