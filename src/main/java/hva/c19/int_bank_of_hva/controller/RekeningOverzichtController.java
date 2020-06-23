package hva.c19.int_bank_of_hva.controller;

import hva.c19.int_bank_of_hva.model.Klant;
import hva.c19.int_bank_of_hva.model.Rekening;
import hva.c19.int_bank_of_hva.service.KlantService;
import hva.c19.int_bank_of_hva.service.RekeningService;
import hva.c19.int_bank_of_hva.service.ZakelijkRekeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("gebruikersnaam")
public class RekeningOverzichtController {

    @Autowired
    private final KlantService klantService;
    private final RekeningService rekeningService;

    @Autowired
    public RekeningOverzichtController(KlantService klantService, RekeningService rekeningService) {
        this.klantService = klantService;
        this.rekeningService = rekeningService;
        }

    public RekeningOverzichtController() {this(null, null);}

    @GetMapping("rekeningOverzichtParticulier")
    public String listParticulierRekeningen(Model model) {
        String gebruikersnaam = (String) model.getAttribute("gebruikersnaam");
        Klant klant = klantService.findKlantByGebruikersnaam(gebruikersnaam);
        List<Rekening> listParticulierRekeningen = rekeningService.listParticulierRekeningen(gebruikersnaam);
//        System.out.println(listParticulierRekeningen.get(0));
        if (!listParticulierRekeningen.isEmpty()) {
//            model.addAttribute("klant", klant);
            model.addAttribute("listParticulierRekeningen", listParticulierRekeningen);
            model.addAttribute("voornaam", klant.getVoornaam());
            model.addAttribute("tussenvoegsel", klant.getTussenvoegsel());
            model.addAttribute("achternaam", klant.getAchternaam());
            model.addAttribute("gebruikersnaam", gebruikersnaam);
            return "rekeningOverzichtParticulier";
        } else return "rekeningOverzichtError";
    }

    @GetMapping("rekeningOverzichtZakelijk")
    public String listZakelijkRekeningen(Model model) {
        String gebruikersnaam = (String) model.getAttribute("gebruikersnaam");
        Klant klant = klantService.findKlantByGebruikersnaam(gebruikersnaam);
        List<Rekening> listZakelijkRekeningen = rekeningService.listZakelijkRekeningen(gebruikersnaam);
        //  System.out.println(listZakelijkRekeningen.get(0));
        if (!listZakelijkRekeningen.isEmpty()) {
            model.addAttribute("listZakelijkRekeningen", listZakelijkRekeningen);
            model.addAttribute("gebruikersnaam", gebruikersnaam);
            return "rekeningOverzichtZakelijk";
        } else return "rekeningOverzichtError";
    }
}
