package hva.c19.int_bank_of_hva.service;

import hva.c19.int_bank_of_hva.model.RekeninghouderVeiligCode;
import hva.c19.int_bank_of_hva.repositories.RekeninghouderVeiligCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RekeninghouderVeiligCodeService {
    @Autowired
    private final RekeninghouderVeiligCodeRepository rekeninghouderVeiligCodeRepository;

    public RekeninghouderVeiligCodeService(RekeninghouderVeiligCodeRepository rekeninghouderVeiligCodeRepository) {
        super();
        this.rekeninghouderVeiligCodeRepository = rekeninghouderVeiligCodeRepository;
    }

    public void saveParticulierVeiligCode(RekeninghouderVeiligCode rekeninghouderVeiligCode) {
        rekeninghouderVeiligCodeRepository.save(rekeninghouderVeiligCode);
    }

    public RekeninghouderVeiligCode getByVeiligCode(int veiligCode) {
        return rekeninghouderVeiligCodeRepository.findByVeiligCode(veiligCode);
    }

}
