package hva.c19.int_bank_of_hva.service;

import hva.c19.int_bank_of_hva.model.ParticulierRekening;
import hva.c19.int_bank_of_hva.model.Rekening;
import hva.c19.int_bank_of_hva.model.ZakelijkRekening;
import hva.c19.int_bank_of_hva.repositories.RekeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RekeningService {
    @Autowired
    private final RekeningRepository rekeningRepository;

    // Constructor
    public RekeningService(RekeningRepository rekeningRepository) {
        this.rekeningRepository = rekeningRepository;
    }

    // Methods
    public void saveRekening(Rekening rekening) {
        rekeningRepository.save(rekening);
    }

    public String getHoogsteZakelijkRekeningnummer() {
        return rekeningRepository.hoogsteZakelijkRekeningnummer();
    }

    public String getHoogsteParticulierRekeningnummer() {
        return rekeningRepository.hoogsteParticulierRekeningnummer();
    }

    public List<ZakelijkRekening> tienBedrijfvenMetHoogsteSaldo() {
        return rekeningRepository.bedrijvenMetHoogsteSaldo();
    }
    public List<ZakelijkRekening> gemiddeldeSaldoPerSector() {
        return rekeningRepository.gemiddeldeSaldoPerSector();
    }

    public Rekening findRekeningById(Integer id) {
        Optional<Rekening> optional = rekeningRepository.findById(id);
        return optional.orElse(null);
    }
    public Rekening findRekeningByBedrijfsnaam(String naam){
        List<Rekening> rekenings = rekeningRepository.findByBedrijfsnaam(naam);
        return rekenings.get(0);
    }

    public Rekening getRekeningByRekeningNr(String rekeningNrDebet) {
        return rekeningRepository.findByRekeningnummer(rekeningNrDebet);
    }

    public List<ZakelijkRekening> rekeninglijst() {
        return rekeningRepository.alleZakelijkRekeningen();
    }

    public List<Rekening> listParticulierRekeningen(String gebruikersnaam) {
        return rekeningRepository.particulierRekeningen(gebruikersnaam);
    }

    public List<Rekening> listZakelijkRekeningen(String gebruikersnaam) {
        return rekeningRepository.zakelijkRekeningen(gebruikersnaam);
    }

    public Rekening getRekeningByRekeningnummer(String rekeningnummer) {
        return rekeningRepository.findByRekeningnummer(rekeningnummer);
   }
}

