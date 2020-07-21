package hva.c19.int_bank_of_hva.controller;

import hva.c19.int_bank_of_hva.service.KlantService;
import hva.c19.int_bank_of_hva.utilities.KlantBackingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("gebruikersnaam")
public class WordenKlantController {
    private final static String BSN_ERROR = "De door u ingevoerde BSN bestaat al !";
    private final static String EMAILADRES_ERROR = "Het door u ingevoerde emailadres is al in gebruik !";
    private final static String GEBRUIKERSNAAM_ERROR = "De door u gekozen gebruikersnaam is al in gebruik !";

    @Autowired
    private final KlantService klantService;


    //Constructors
    public WordenKlantController() {
        this(null);
    }

    public WordenKlantController(KlantService klantService) {
        this.klantService = klantService;

    }

    /**
     * Om een klant te registreren en opslaan in de database.
     * Checkt eerst als bsn,emailadres en gebruikersnaam zijn unique.
     *
     * @param klantBackingBean backingbean nieuwe klant
     * @return wordenKlantRegistreren.html
     */
    @PostMapping("/registreren_klant")
    public ModelAndView handelRegistrerenKlant(@ModelAttribute KlantBackingBean klantBackingBean) {
        ModelAndView mav;
        if (klantService.isBestaandeBsn(klantBackingBean.getBsn())) {
            mav = getErrorMAV(klantBackingBean, BSN_ERROR);
        } else if (klantService.isBestaandeEmailadres(klantBackingBean.getEmailadres())) {
            mav = getErrorMAV(klantBackingBean, EMAILADRES_ERROR);
        } else if (klantService.isBestaandeGebruikersnaam(klantBackingBean.getGebruikersnaam())) {
            mav = getErrorMAV(klantBackingBean, GEBRUIKERSNAAM_ERROR);
        } else {
            klantService.saveKlant(klantBackingBean.klant());
            mav = klantRegistrerenMAV(klantBackingBean);
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
        mav = new ModelAndView("wordenKlant");
        mav.addObject("klantBean", klantBackingBean);
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
    private ModelAndView klantRegistrerenMAV(@ModelAttribute KlantBackingBean klantBackingBean) {
        ModelAndView mav;
        mav = new ModelAndView("wordenKlantRegistreren");
        mav.addObject("klant", klantBackingBean);
        mav.addObject("gebruikersnaam", klantBackingBean.getGebruikersnaam());
        return mav;
    }
}
