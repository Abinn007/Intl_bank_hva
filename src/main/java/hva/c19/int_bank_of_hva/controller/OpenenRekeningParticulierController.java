package hva.c19.int_bank_of_hva.controller;

import hva.c19.int_bank_of_hva.model.Klant;
import hva.c19.int_bank_of_hva.model.ParticulierRekening;
import hva.c19.int_bank_of_hva.model.Rekening;
import hva.c19.int_bank_of_hva.service.KlantService;
import hva.c19.int_bank_of_hva.service.RekeningService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("gebruikersnaam")
public class OpenenRekeningParticulierController {
    private static final double BEGIN_SALDO = 0.0;
    private static final String CONSTANT_DEEL_REKENINGNUMMER = "NL35IBVH";

    private final KlantService klantService;
    private final RekeningService rekeningService;

    public OpenenRekeningParticulierController(KlantService klantService, RekeningService rekeningService) {
        this.klantService = klantService;
        this.rekeningService = rekeningService;
    }

    @GetMapping("openen_rekening_particulier")
    public ModelAndView handelOpenenRekeningParticulier(Model model){
        ModelAndView mav = new ModelAndView("openenRekeningBevestiging");
        String gebruikersnaam = (String) model.getAttribute("gebruikersnaam");
        Klant klant = klantService.findKlantByGebruikersnaam(gebruikersnaam);
        Rekening rekening = getRekening(klant);
        mav.addObject("klant", klant);
        mav.addObject("rekeningnummer", rekening.getRekeningnummer());
        rekeningService.saveRekening(rekening);
        return mav;
    }

    private Rekening getRekening(Klant klant) {
        Rekening rekening = new ParticulierRekening();
        rekening.klantToevoegen(klant);
        rekening.setSaldo(BEGIN_SALDO);
        if (rekeningService.getHoogsteParticulierRekeningnummer() == null) {
            rekening.setRekeningnummer(CONSTANT_DEEL_REKENINGNUMMER + "1000000000");
        } else {
            long hoogsteRekeningnummer = Long.parseLong(rekeningService.getHoogsteParticulierRekeningnummer().substring(8));
            rekening.setRekeningnummer(CONSTANT_DEEL_REKENINGNUMMER + (hoogsteRekeningnummer + 1));
        }
        return rekening;
    }
}
