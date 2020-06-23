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
public class WordenKlantRegistrerenController {
    @Autowired
    private final KlantService klantService;

    //Constructors
    public WordenKlantRegistrerenController() {
        this(null);
    }

    public WordenKlantRegistrerenController(KlantService klantService) {
        this.klantService = klantService;
    }

    /**
     * Zodra de klant geregisteerd is, kan hij verder gaan met een rekening te openen.
     *
     * @param model
     * @return
     */
    @GetMapping("/openen_rekening")
    public String openenRekeninghandler(Model model) {
        String gebruikersnaam = (String) model.getAttribute("gebruikersnaam");
        Klant klant = klantService.findKlantByGebruikersnaam(gebruikersnaam);
        model.addAttribute("klant", klant);
        return "openenRekening";
    }
}
