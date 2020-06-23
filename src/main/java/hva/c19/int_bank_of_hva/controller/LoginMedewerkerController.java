package hva.c19.int_bank_of_hva.controller;

import hva.c19.int_bank_of_hva.model.Medewerker;
import hva.c19.int_bank_of_hva.service.HoofdParticulierService;
import hva.c19.int_bank_of_hva.service.HoofdZakelijkService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Controller
public class LoginMedewerkerController {
    private final HoofdParticulierService hoofdParticulierService;
    private final HoofdZakelijkService hoofdZakelijkService;

    public LoginMedewerkerController(HoofdParticulierService hoofdParticulierService, HoofdZakelijkService hoofdZakelijkService) {
        super();
        this.hoofdParticulierService = hoofdParticulierService;
        this.hoofdZakelijkService = hoofdZakelijkService;
    }

    @GetMapping("medewerkerInloggen")
    public ModelAndView handleMedewerkerInloggen(@RequestParam String gebruikersnaam, @RequestParam String wachtwoord) {
        ModelAndView mav = null;
        Medewerker particulierMedewerker = hoofdParticulierService.getMedewerkerByGebruikersnaam(gebruikersnaam);
        Medewerker zakelijkMedewerker = hoofdZakelijkService.getMedewerkerByGebruikersnaam(gebruikersnaam);
        if (particulierMedewerker != null && particulierMedewerker.getGebruikersnaam().equals(gebruikersnaam) && particulierMedewerker.getWachtwoord().equals(wachtwoord)
        || zakelijkMedewerker != null && zakelijkMedewerker.getGebruikersnaam().equals(gebruikersnaam) && zakelijkMedewerker.getWachtwoord().equals(wachtwoord)) {
           if (zakelijkMedewerker == null) {
               mav = new ModelAndView("hoofdParticulieren");
           } else if(particulierMedewerker == null) {
               mav = new ModelAndView("hoofdZakelijk");
           }
        } else {
            mav = new ModelAndView("loginMedewerker");
            mav.addObject("message", "<div style= 'margin-bottom: 5px; border: 2px solid red; color: #0069c0; font-size: 17px;" +
                    " border-radius: 5px;' ><i class=\"fa fa-exclamation-circle\" style=\"color: red;\" aria-hidden=\"true\"></i>  Je inloggegevens kloppen niet, controleer de combinatie van je gebruikersnaam en wachtwoor.️</div>");
        }
        return mav;
    }

}
