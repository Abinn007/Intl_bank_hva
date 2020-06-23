package hva.c19.int_bank_of_hva.service;
import hva.c19.int_bank_of_hva.model.Klant;

import hva.c19.int_bank_of_hva.model.Rekening;
import hva.c19.int_bank_of_hva.model.ZakelijkRekening;
import hva.c19.int_bank_of_hva.repositories.ZakelijkRekeningRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ZakelijkRekeningService {
    private final ZakelijkRekeningRepository zakelijkRekeningRepository;

    public ZakelijkRekeningService(ZakelijkRekeningRepository zakelijkRekeningRepository) {
        super();
        this.zakelijkRekeningRepository = zakelijkRekeningRepository;
    }

    public ZakelijkRekening getZakelijkRekeningByRekeningNr(String  rekeningNr) {
        return zakelijkRekeningRepository.findByRekeningnummer(rekeningNr);
    }


}
