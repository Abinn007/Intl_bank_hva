package hva.c19.int_bank_of_hva.controller;

import hva.c19.int_bank_of_hva.model.*;
import hva.c19.int_bank_of_hva.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("gebruikersnaam")
public class RekeninghouderToevoegenController {
    private final RekeninghouderVeiligCodeService rekeninghouderVeiligCodeService;
    private final RekeningService rekeningService;
    private final KlantService klantService;

    public RekeninghouderToevoegenController(RekeninghouderVeiligCodeService rekeninghouderVeiligCodeService, RekeningService rekeningService, KlantService klantService) {
        super();
        this.rekeninghouderVeiligCodeService = rekeninghouderVeiligCodeService;
        this.rekeningService = rekeningService;
        this.klantService = klantService;
    }


    @GetMapping("/rekeninghouder")
    public String handelRekeninghouder(@RequestParam("rekeningNr") String rekeningNr, Model model) {
//        String gebruikersnaam = (String) model.getAttribute("gebruikersnaam");
        model.addAttribute("rekeningnummer", rekeningNr);
        System.out.println(rekeningNr);
        return "rekeninghouderToevoegen";
    }

    @PostMapping("rekeninghouderToevoegen")
    public String handelRekeninghouderToevoegen(@RequestParam("rekeningNr") String rekeningNr, @RequestParam String rekeninghouderGebruikersnaam, @RequestParam int veiligCode, Model model) {
        model.addAttribute("rekeningnummer", rekeningNr);
        String gebruikersnaam = (String) model.getAttribute("gebruikersnaam");
        model.addAttribute("rekeninghouderGebruikersnaam", rekeninghouderGebruikersnaam);
        model.addAttribute("veiligCode", veiligCode);
        Klant rekeninghouder = klantService.findKlantByGebruikersnaam(rekeninghouderGebruikersnaam);
        Klant klant = klantService.findKlantByGebruikersnaam(gebruikersnaam);
        Rekening rekening = rekeningService.getRekeningByRekeningNr(rekeningNr);
        if (rekeninghouder != null) {
                RekeninghouderVeiligCode rekeninghouderVeiligCode = new RekeninghouderVeiligCode(veiligCode, rekeninghouder, rekening);
                rekeninghouderVeiligCodeService.saveParticulierVeiligCode(rekeninghouderVeiligCode);
        } else {
            model.addAttribute("message", "Er is geen klant met '" + rekeninghouderGebruikersnaam + "' gebruikersnaam!" + ".");
            return "rekeninghouderToevoegen";
        }

        return "rekeningOverzicht";
    }

}
