package hva.c19.int_bank_of_hva.controller;

import hva.c19.int_bank_of_hva.model.Klant;
import hva.c19.int_bank_of_hva.service.KlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("gebruikersnaam")
public class RekeningOverzichtParticulierController {

    @Autowired
    private final KlantService klantService;

    public RekeningOverzichtParticulierController(KlantService klantService) {
        this.klantService = klantService;
    }

    public RekeningOverzichtParticulierController() {this(null);}

    @GetMapping("/beheerRekening")
    public String handleBeheerRekening(@RequestParam("rekeningNr") String rekeningNr, Model model) {
        System.out.println(rekeningNr);
        String gebruikersnaam = (String) model.getAttribute("gebruikersnaam");
        Klant klant = klantService.findKlantByGebruikersnaam(gebruikersnaam);
        model.addAttribute("rekeningnummer",rekeningNr);
        model.addAttribute("voornaam", klant.getVoornaam());
        model.addAttribute("tussenvoegsel", klant.getTussenvoegsel());
        model.addAttribute("achternaam", klant.getAchternaam());
        model.addAttribute("gebruikersnaam", klant.getGebruikersnaam());
        return "beheerRekening";
    }
}
