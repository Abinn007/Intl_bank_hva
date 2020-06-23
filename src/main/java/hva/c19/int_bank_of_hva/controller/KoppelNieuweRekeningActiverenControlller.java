package hva.c19.int_bank_of_hva.controller;

import hva.c19.int_bank_of_hva.model.*;
import hva.c19.int_bank_of_hva.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@SessionAttributes("gebruikersnaam")
public class KoppelNieuweRekeningActiverenControlller {
    private final RekeninghouderVeiligCodeService rekeninghouderVeiligCodeService;
    private final RekeningService rekeningService;
    private final KlantService klantService;

    public KoppelNieuweRekeningActiverenControlller(RekeninghouderVeiligCodeService rekeninghouderVeiligCodeService, RekeningService rekeningService, KlantService klantService) {
        super();
        this.rekeninghouderVeiligCodeService = rekeninghouderVeiligCodeService;
        this.rekeningService = rekeningService;
        this.klantService = klantService;
    }

    @GetMapping("koppelNieuweRekeningActiveren")
    public String handleKoppelRekening() {
        return "koppelNieuweRekeningActiveren";
    }

    @PostMapping("rekeningnummerActiveren")
    public String  handleRekeningnummerActiveren(@RequestParam String rekeningnummer, @RequestParam int veiligCode, Model model) {
        String gebruikersnaam = (String) model.getAttribute("gebruikersnaam");
        Klant klant = klantService.findKlantByGebruikersnaam(gebruikersnaam);
        Rekening rekening = rekeningService.getRekeningByRekeningNr(rekeningnummer);
        RekeninghouderVeiligCode rekeninghouder = rekeninghouderVeiligCodeService.getByVeiligCode(veiligCode);
        model.addAttribute("rekeningnummer", rekeningnummer);
        model.addAttribute("veiligCode", veiligCode);
        if (rekening != null && rekeninghouder != null && rekeninghouder.getVeiligCode() == veiligCode) {
            rekening.klantToevoegen(klant);
            rekeningService.saveRekening(rekening);
        } else {
            model.addAttribute("message", "Je rekening gegevens kloppen niet, controleer de combinasi van rekeningnummer en veiligcode.");
            return "koppelNieuweRekeningActiveren";
        }
        return "rekeningOverzicht";
    }
}
