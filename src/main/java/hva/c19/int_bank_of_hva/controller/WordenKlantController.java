package hva.c19.int_bank_of_hva.controller;

import hva.c19.int_bank_of_hva.service.KlantService;
import hva.c19.int_bank_of_hva.utilities.KlantBackingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("gebruikersnaam")
public class WordenKlantController {
    private final static String BSN_ERROR = "De door u ingevoerde BSN bestaat al!";
    private final static String EMAILADRES_ERROR = "De door u ingevoerde emailadres is al in gebruik!";
    private final static String GEBRUIKERSNAAM_ERROR = "De door u gekozen gebruikersnaam is al in gebruik!";

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
     * Om een nieuwe klant te worden
     *
     * @return wordenKlant.html
     */
    @GetMapping("/nieuwe_klant")
    public ModelAndView handelNieuwKlant() {
        ModelAndView mav = new ModelAndView("wordenKlant");
        KlantBackingBean klantBackingBean = new KlantBackingBean();
        mav.addObject("klantBean", klantBackingBean);
        return mav;
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
            mav = bsnErrorModelAndView(klantBackingBean);
        } else if (klantService.isBestaandeEmailadres(klantBackingBean.getEmailadres())) {
            mav = EmailadresErrorModelAndView(klantBackingBean);
        } else if (klantService.isBestaandeGebruikersnaam(klantBackingBean.getGebruikersnaam())) {
            mav = gebruikersnaamErrorModelAndView(klantBackingBean);
        } else {
            klantService.saveKlant(klantBackingBean.klant());
            mav = klantRegistrerenModelAndView(klantBackingBean);
        }
        return mav;
    }

    /**
     * Om een melding pagina te gaan als de ingevoerde BSN al is gebruik is.
     *
     * @param klantBackingBean backingbean nieuwe klant
     * @return wordenKlantError.html
     */
    private ModelAndView bsnErrorModelAndView(@ModelAttribute KlantBackingBean klantBackingBean) {
        ModelAndView mav;
        mav = new ModelAndView("wordenKlantError");
        mav.addObject("error", BSN_ERROR);
        mav.addObject("gebruikersnaam", klantBackingBean.getGebruikersnaam());
        return mav;
    }

    /**
     * Om een melding pagina te gaan als de ingevoerde emailadres al in gebruik is.
     *
     * @param klantBackingBean backingbean nieuwe klant
     * @return wordenKlantError.html
     */
    private ModelAndView EmailadresErrorModelAndView(@ModelAttribute KlantBackingBean klantBackingBean) {
        ModelAndView mav;
        mav = new ModelAndView("wordenKlantError");
        mav.addObject("error", EMAILADRES_ERROR);
        mav.addObject("gebruikersnaam", klantBackingBean.getGebruikersnaam());
        return mav;
    }

    /**
     * Om een melding pagina te gaan als de gekozen gebruikersnaam al in gebruik is.
     *
     * @param klantBackingBean backingbean nieuwe klant
     * @return wordenKlantError.html
     */
    private ModelAndView gebruikersnaamErrorModelAndView(@ModelAttribute KlantBackingBean klantBackingBean) {
        ModelAndView mav;
        mav = new ModelAndView("wordenKlantError");
        mav.addObject("error", GEBRUIKERSNAAM_ERROR);
        mav.addObject("gebruikersnaam", klantBackingBean.getGebruikersnaam());
        return mav;
    }

    /**
     * Om naar registreren bevestiging pagina te gaan.
     *
     * @param klantBackingBean backingbean nieuwe klant
     * @return wordenKlantRegistreren.html
     */
    private ModelAndView klantRegistrerenModelAndView(@ModelAttribute KlantBackingBean klantBackingBean) {
        ModelAndView mav;
        mav = new ModelAndView("wordenKlantRegistreren");
        mav.addObject("klant", klantBackingBean);
        mav.addObject("gebruikersnaam", klantBackingBean.getGebruikersnaam());
        return mav;
    }
}
