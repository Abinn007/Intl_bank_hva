package hva.c19.int_bank_of_hva.controller;

import hva.c19.int_bank_of_hva.model.Klant;
import hva.c19.int_bank_of_hva.service.KlantService;
import hva.c19.int_bank_of_hva.utilities.KlantBackingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("gebruikersnaam")
public class WelkomController {
    private final static String BSN_ERROR = "U kunt het BSN niet wijzigen !";
    private final static String EMAILADRES_ERROR = "Het door u ingevoerde emailadres is al in gebruik !";
    private final static String GEBRUIKERSNAAM_ERROR = "De door u gekozen gebruikersnaam is al in gebruik !";

    @Autowired
    private final KlantService klantService;

    public WelkomController() {this(null);}

    public WelkomController(KlantService klantService) {this.klantService = klantService;
    }

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

    /**
     * Om de klantgegevens te wijzigen
     * @param model
     * @return
     */

    @GetMapping("/gegevensWijzigen")
    public String gegevensWijzigenHandler(Model model){
        String gebruikersnaam = (String) model.getAttribute("gebruikersnaam");
        Klant klant = klantService.findKlantByGebruikersnaam(gebruikersnaam);
        model.addAttribute("klant", klant);
        return "klantGegevensWijzigen";
    }

    /**
     * Om de gewijzigde klantgegevns op te slaan.
     * @param klantBackingBean
     * @param model
     * @return
     */
    @PostMapping("/wijzigen_oplsaan")
    public ModelAndView handelRegistrerenKlant(@ModelAttribute KlantBackingBean klantBackingBean, Model model) {
        ModelAndView mav;
        String gebruikersnaam = (String) model.getAttribute("gebruikersnaam");
        Klant klant = klantService.findKlantByGebruikersnaam(gebruikersnaam);
        if (klantService.isOngeldigBsn(klantBackingBean.getBsn(),klant)){
            mav = getErrorMAV(klantBackingBean, BSN_ERROR);
        } else if (klantService.isOngeldigEmailadres(klantBackingBean.getEmailadres(),klant)) {
            mav = getErrorMAV(klantBackingBean, EMAILADRES_ERROR);
        } else if (klantService.isOngeldigGebruikersnaam(klantBackingBean.getGebruikersnaam(),klant)) {
            mav = getErrorMAV(klantBackingBean, GEBRUIKERSNAAM_ERROR);
        } else {
            mav = getOpslaanWijzigingMAV(klantBackingBean, klant);
        }
        return mav;
    }

    /**
     * Om een melding te laten zien als de ingevoerde BSN,
     * emailadres of gebruikersnaam al in gebruik zijn.
     *
     * @param klantBackingBean backingbean nieuwe klant
     * @param error error bericht
     * @return errorMAV
     */
    private ModelAndView getErrorMAV(@ModelAttribute KlantBackingBean klantBackingBean, String error) {
        ModelAndView mav;
        mav = new ModelAndView("klantGegevensWijzigen");
        mav.addObject("klant", klantBackingBean);
        mav.addObject("error", error);
        mav.addObject("gebruikersnaam", klantBackingBean.getGebruikersnaam());
        return mav;
    }

    /**
     * Om naar registreren bevestiging pagina te gaan.
     *
     * @param klantBackingBean backingbean nieuwe klant
     * @return wordenKlantRegistreren.html
     */
    private ModelAndView getOpslaanWijzigingMAV(@ModelAttribute KlantBackingBean klantBackingBean, Klant klant) {
        ModelAndView mav;
        Klant nwKlant = klantBackingBean.updateKlant();
        nwKlant.setKlantNr(klant.getKlantNr());
        klantService.saveKlant(nwKlant);
        mav = new ModelAndView("klantGegevensOpslaan");
        mav.addObject("klant", klantBackingBean);
        mav.addObject("gebruikersnaam", klantBackingBean.getGebruikersnaam());
        return mav;
    }

}
