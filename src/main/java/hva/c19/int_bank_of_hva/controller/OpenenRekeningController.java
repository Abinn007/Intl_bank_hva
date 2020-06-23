package hva.c19.int_bank_of_hva.controller;

import hva.c19.int_bank_of_hva.model.Klant;
import hva.c19.int_bank_of_hva.service.KlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("gebruikersnaam")
public class  OpenenRekeningController {
    @Autowired
    private final KlantService klantService;

    public OpenenRekeningController() {
        this(null);
    }

    public OpenenRekeningController(KlantService klantService) {
        this.klantService = klantService;
    }

    @GetMapping("/openenRekeningParticulier")
    public String openenRekeningParticulierHandler(Model model) {
        String gebruikersnaam = (String) model.getAttribute("gebruikersnaam");
        Klant klant = klantService.findKlantByGebruikersnaam(gebruikersnaam);
        model.addAttribute("klant", klant);
        return "openenRekeningParticulier";
    }

    @GetMapping("/openenRekeningZakelijk")
    public String openenRekeningZakelijkHandler(Model model) {
        String gebruikersnaam = (String) model.getAttribute("gebruikersnaam");
        Klant klant = klantService.findKlantByGebruikersnaam(gebruikersnaam);
        model.addAttribute("klant", klant);
        return "openenRekeningZakelijk1";
    }
}
