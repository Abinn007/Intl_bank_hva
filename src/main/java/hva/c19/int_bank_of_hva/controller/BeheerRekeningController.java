package hva.c19.int_bank_of_hva.controller;

import hva.c19.int_bank_of_hva.model.Klant;
import hva.c19.int_bank_of_hva.model.ParticulierRekening;
import hva.c19.int_bank_of_hva.model.ZakelijkRekening;
import hva.c19.int_bank_of_hva.service.KlantService;
import hva.c19.int_bank_of_hva.service.RekeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("gebruikersnaam")
public class BeheerRekeningController {

    @Autowired
    private final KlantService klantService;

    @Autowired
    private final RekeningService rekeningService;

    public BeheerRekeningController(KlantService klantService, RekeningService rekeningService) {
        this.klantService = klantService;
        this.rekeningService = rekeningService;
    }

    public BeheerRekeningController() {this(null, null);}

    @GetMapping("/beheerRekening")
    public String handleBeheerRekening(@RequestParam("rekeningNr") String rekeningNr, Model model) {
        String gebruikersnaam = (String) model.getAttribute("gebruikersnaam");
        int rekeningId = rekeningService.getRekeningByRekeningNr(rekeningNr).getRekeningId();
        model.addAttribute("rekeningnummer",rekeningNr);
        model.addAttribute("rekeninghouders", klantService.rekeninghouders(rekeningId));
        model.addAttribute("gebruikersnaam", gebruikersnaam);
        return "beheerRekening";
    }

    @GetMapping("/beheerRekeningZakelijk")
    public String handleBeheerRekeningZakelijk(@RequestParam("rekeningNr") String rekeningNr, Model model) {
        String gebruikersnaam = (String) model.getAttribute("gebruikersnaam");
        int rekeningId = rekeningService.getRekeningByRekeningNr(rekeningNr).getRekeningId();
        ZakelijkRekening zakelijkRekening = (ZakelijkRekening) rekeningService.getRekeningByRekeningnummer(rekeningNr);
        model.addAttribute("rekeningnummer", rekeningNr);
        model.addAttribute("rekeninghouders", klantService.rekeninghouders(rekeningId));
        model.addAttribute("bedrijfsnaam", zakelijkRekening.getBedrijfsnaam());
        System.out.println(zakelijkRekening.getBedrijfsnaam());
        model.addAttribute("gebruikersnaam", gebruikersnaam);
        return "beheerRekeningZakelijk";
    }


}
