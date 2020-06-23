package hva.c19.int_bank_of_hva.controller;

import hva.c19.int_bank_of_hva.model.Klant;
import hva.c19.int_bank_of_hva.service.KlantService;
import hva.c19.int_bank_of_hva.service.ZakelijkRekeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("gebruikersnaam")
public class RekeningOverzichtZakelijkController {

    @Autowired
    private final KlantService klantService;

    @Autowired
    private final ZakelijkRekeningService zakelijkRekeningService;

    public RekeningOverzichtZakelijkController(KlantService klantService, ZakelijkRekeningService zakelijkRekeningService) {
        this.klantService = klantService;
        this.zakelijkRekeningService = zakelijkRekeningService;
    }

    public RekeningOverzichtZakelijkController() {this(null, null);}

    @GetMapping("/beheerRekeningZakelijk")
    public String handleBeheerRekeningZakelijk(@RequestParam("rekeningNr") String rekeningNr, Model model) {
        System.out.println(rekeningNr);
        String gebruikersnaam = (String) model.getAttribute("gebruikersnaam");
        Klant klant = klantService.findKlantByGebruikersnaam(gebruikersnaam);
        model.addAttribute("rekeningnummer",rekeningNr);
        model.addAttribute("bedrijfsnaam", zakelijkRekeningService.getZakelijkRekeningByRekeningNr(rekeningNr).getBedrijfsnaam());
        System.out.println(zakelijkRekeningService.getZakelijkRekeningByRekeningNr(rekeningNr).getBedrijfsnaam());
        model.addAttribute("gebruikersnaam", klant.getGebruikersnaam());
        return "beheerRekeningZakelijk";
    }
}
