package hva.c19.int_bank_of_hva.controller;

import hva.c19.int_bank_of_hva.model.*;
import hva.c19.int_bank_of_hva.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("gebruikersnaam")
public class RekeninghouderToevoegenController {
    final static String ERROR_MESSAGE = "<div style= 'margin-bottom: 5px; border: 2px solid red; color: #0069c0; font-size: 17px;border-radius: 5px;' ><i class= 'fa fa-exclamation-circle' style='color: red; aria-hidden=true'></i> Er is geen klant met deze gebruikersnaam.</div>";
    @Autowired
    private final RekeninghouderVeiligCodeService rekeninghouderVeiligCodeService;
    @Autowired
    private final RekeningService rekeningService;
    @Autowired
    private final KlantService klantService;


    public RekeninghouderToevoegenController(RekeninghouderVeiligCodeService rekeninghouderVeiligCodeService, RekeningService rekeningService, KlantService klantService) {
        this.rekeninghouderVeiligCodeService = rekeninghouderVeiligCodeService;
        this.rekeningService = rekeningService;
        this.klantService = klantService;
    }


    @GetMapping("/rekeninghouder")
    public String handelRekeninghouder(@RequestParam("rekeningNr") String rekeningNr, Model model) {
        model.addAttribute("rekeningnummer", rekeningNr);
        System.out.println(rekeningNr);
        return "rekeninghouderToevoegen";
    }

    @PostMapping("rekeninghouderToevoegen")
    public String handelRekeninghouderToevoegen(@RequestParam("rekeningNr") String rekeningNr, @RequestParam String rekeninghouderGebruikersnaam, @RequestParam int veiligCode, Model model) {
        model.addAttribute("rekeningnummer", rekeningNr);
        model.addAttribute("rekeninghouderGebruikersnaam", rekeninghouderGebruikersnaam);
        model.addAttribute("veiligCode", veiligCode);
        Klant rekeninghouder = klantService.findKlantByGebruikersnaam(rekeninghouderGebruikersnaam);
        Rekening rekening = rekeningService.getRekeningByRekeningNr(rekeningNr);
        if (rekeninghouder != null) {
                RekeninghouderVeiligCode rekeninghouderVeiligCode = new RekeninghouderVeiligCode(veiligCode, rekeninghouder, rekening);
                rekeninghouderVeiligCodeService.saveParticulierVeiligCode(rekeninghouderVeiligCode);
        } else {
            model.addAttribute("message", ERROR_MESSAGE );
            return "rekeninghouderToevoegen";
        }
        return "rekeningOverzicht";
    }

}
