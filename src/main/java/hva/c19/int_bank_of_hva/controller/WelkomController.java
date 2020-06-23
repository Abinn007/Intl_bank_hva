package hva.c19.int_bank_of_hva.controller;

import hva.c19.int_bank_of_hva.model.Klant;
import hva.c19.int_bank_of_hva.repositories.KlantRepository;
import hva.c19.int_bank_of_hva.service.KlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("gebruikersnaam")
public class WelkomController {

    @Autowired
    private final KlantService klantService;

    public WelkomController() {this(null);}

    public WelkomController(KlantService klantService) {this.klantService = klantService;}

    @GetMapping("/welkom")
    public String welkomHandler(Model model) {
        String gebruikersnaam = (String) model.getAttribute("gebruikersnaam");
        Klant klant = klantService.findKlantByGebruikersnaam(gebruikersnaam);
        model.addAttribute("klant", klant);
        return "welkom";
    }

    @GetMapping("/openRekening")
    public String openRekeningHandler(Model model) {
    String gebruikersnaam = (String) model.getAttribute("gebruikersnaam");
    Klant klant = klantService.findKlantByGebruikersnaam(gebruikersnaam);
    model.addAttribute("klant", klant);
    return "openenRekening";
    }

    @GetMapping("/rekeningOverzicht")
    public String rekeningOverzichtHandler(Model model) {
        String gebruikersnaam = (String) model.getAttribute("gebruikersnaam");
        Klant klant = klantService.findKlantByGebruikersnaam(gebruikersnaam);
        model.addAttribute("klant", klant);
        model.addAttribute("gebruikersnaam", gebruikersnaam);
        return "rekeningOverzicht";
    }
}
