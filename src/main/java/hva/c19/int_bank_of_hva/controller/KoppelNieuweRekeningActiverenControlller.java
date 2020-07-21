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
public class KoppelNieuweRekeningActiverenControlller {
    private final static String ERROR_MESSAGE = "<div style= 'margin-bottom: 5px; border: 2px solid red; color: #0069c0; font-size: 17px;border-radius: 5px;' ><i class= 'fa fa-exclamation-circle' style='color: red; aria-hidden=true'></i> Uw rekening gegevens kloppen niet.</div>";

    @Autowired
    private final RekeninghouderVeiligCodeService rekeninghouderVeiligCodeService;
    @Autowired
    private final RekeningService rekeningService;
    @Autowired
    private final KlantService klantService;


    // CONSTRUCTOR
    public KoppelNieuweRekeningActiverenControlller(RekeninghouderVeiligCodeService rekeninghouderVeiligCodeService, RekeningService rekeningService, KlantService klantService) {
        super();
        this.rekeninghouderVeiligCodeService = rekeninghouderVeiligCodeService;
        this.rekeningService = rekeningService;
        this.klantService = klantService;
    }

    /***
     * Geef de koppel nieuwe rekeningnummer activeren pagina en het formulier weer
     * @return koppelNieuweRekeningActiveren.html
     */
    @GetMapping("koppelNieuweRekeningActiveren")
    public String handleKoppelRekening() {
        return "koppelNieuweRekeningActiveren";
    }

    /**
     * om koppel nieuwe rekeningnummer te activeren
     * @param rekeningnummer
     * @param veiligCode
     * @return rekeningOverzicht.html
     */
    @PostMapping("rekeningnummerActiveren")
    public String  handleRekeningnummerActiveren(@RequestParam String rekeningnummer, @RequestParam int veiligCode, Model model) {
        String gebruikersnaam = (String) model.getAttribute("gebruikersnaam");
        model.addAttribute("rekeningnummer", rekeningnummer);
        model.addAttribute("veiligCode", veiligCode);
        Klant klant = klantService.findKlantByGebruikersnaam(gebruikersnaam);
        Rekening rekening = rekeningService.getRekeningByRekeningNr(rekeningnummer);
        if (rekening == null) {
            model.addAttribute("message", ERROR_MESSAGE );
            return "koppelNieuweRekeningActiveren";
        }
        RekeninghouderVeiligCode rekeninghouder = rekeninghouderVeiligCodeService.getByRekeningId(rekening.getRekeningId());
        if (rekeninghouder == null) {
            model.addAttribute("message", ERROR_MESSAGE );
            return "koppelNieuweRekeningActiveren";
        }
        if (rekeninghouder.getVeiligCode() == veiligCode)
            rekening.klantToevoegen(klant);
            rekeningService.saveRekening(rekening);
        return "rekeningOverzicht";
    }
}
