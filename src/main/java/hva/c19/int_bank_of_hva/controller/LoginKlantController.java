package hva.c19.int_bank_of_hva.controller;

import hva.c19.int_bank_of_hva.model.ConfirmationToken;
import hva.c19.int_bank_of_hva.model.Klant;
import hva.c19.int_bank_of_hva.service.ConfirmationTokenService;
import hva.c19.int_bank_of_hva.service.EmailSenderService;
import hva.c19.int_bank_of_hva.service.KlantService;
import org.apache.catalina.authenticator.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.text.html.HTML;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Controller
@SessionAttributes("gebruikersnaam")
public class LoginKlantController {
    private final static String ERROR_LOGIN_MESSAGE = "<div style= 'margin-bottom: 5px; border: 2px solid red; color: #0069c0; font-size: 17px;" +
            " border-radius: 5px;' ><i class= 'fa fa-exclamation-circle' style='color: red; aria-hidden=true'></i>  Uw inloggegevens kloppen niet.️</div>";
    private final static String SUCCESS_MESSAGE = "Wachtwoord succesvol gereset. U kunt nu inloggen met de nieuwe inloggegevens.";
    private final static String ERROR_LINK_MESSAGE = "<i class='fa fa-exclamation-circle' style='color: red; aria-hidden=true'></i> De link is ongeldig of verbroken!";
    private final static String SUCCESS_LINK_MESSAGE = "Verzoek om opnieuw ingesteld wachtwoord ontvangen. Controleer uw inbox voor de reset-link.";
    private final static String ERROR_GEBRUIKERSNAAM_MESSAGE = "<i class='fa fa-exclamation-circle' style='color: red; font-size:20px; aria-hidden=true'></i> Er is geen gebruikersnaam met ";
    private static final String SALT = "QxLUF1bgIAdeQX";

    @Autowired
    private final KlantService klantService;
    @Autowired
    private final EmailSenderService emailSenderService;
    @Autowired
    private final ConfirmationTokenService confirmationTokenService;

    // CONSTRUCTOR
    public LoginKlantController(KlantService klantService, EmailSenderService emailSenderService, ConfirmationTokenService confirmationTokenService) {
        super();
        this.klantService = klantService;
        this.emailSenderService = emailSenderService;
        this.confirmationTokenService = confirmationTokenService;
    }

    /**
     * Om in te loggen naar welkom pagina
     *
     * @param gebruikersnaam
     * @param wachtwoord
     * @return welkom.html
     */
    @RequestMapping("klantInloggen")
    public ModelAndView handleKlantInloggen(@RequestParam String gebruikersnaam, @RequestParam String wachtwoord) {
        ModelAndView mav = null;
        String veiligeWachtwoord = getGehashedWachtwoord(wachtwoord, SALT);
        Klant klant = klantService.findKlantByGebruikersnaam(gebruikersnaam);
        System.out.println(klant);
        if (klant != null && klant.getGebruikersnaam().equals(gebruikersnaam) && klant.getWachtwoord().equals(veiligeWachtwoord)) {
            mav = new ModelAndView("welkom");
            mav.addObject("klant", klant);
            mav.addObject("gebruikersnaam", klant.getGebruikersnaam());
        } else {
            mav = new ModelAndView("loginKlant");
            mav.addObject("message", ERROR_LOGIN_MESSAGE);
        }
        return mav;
    }

    /**
     * Om een gehashed wachtwoord te genereren van de ingevoerde wachtwoord
     * voor validatie.
     *
     * @param wachtwoord
     * @param salt
     * @return
     */
    public String getGehashedWachtwoord(String wachtwoord, String salt) {
        String hashedWachtwoord = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(wachtwoord.getBytes(StandardCharsets.UTF_8));
            hashedWachtwoord = getString(hashedWachtwoord, bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashedWachtwoord;
    }

    private String getString(String hashedWachtwoord, byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }
        hashedWachtwoord = sb.toString();
        return hashedWachtwoord;
    }

    /**
     * Geef de vergeten wachtwoordpagina en het formulier weer
     * @return wachtwoordVergeten.html
     */
    @GetMapping("/wachtwoordVergeten")
    public String resetWachtwoordWeergeven() {
        return "wachtwoordVergeten";
    }

    /**
     *
     * Ontvang e-mail van de gebruiker, maak een token aan en stuur deze via e-mail naar de gebruiker
     */
    @RequestMapping(value = "/wachtwoord-vergeten", method = RequestMethod.POST)
    public ModelAndView klantWachtwoordVergeten(@RequestParam String gebruikersnaam, ModelAndView mav) {
        Klant klant = klantService.findKlantByGebruikersnaam(gebruikersnaam);
        if (klant != null) {
            ConfirmationToken confirmationToken = new ConfirmationToken(klant);
            confirmationTokenService.saveConfirmationToken(confirmationToken);
            SimpleMailMessage mailMessage = getSimpleMailMessage(klant, confirmationToken);
            emailSenderService.sendEmail(mailMessage);
            mav.addObject("message", SUCCESS_LINK_MESSAGE );
            mav.setViewName("succesResetWachtwoord");
        } else {
            mav.setViewName("wachtwoordVergeten");
            mav.addObject("message",  ERROR_GEBRUIKERSNAAM_MESSAGE + gebruikersnaam + " .");
        }
        return mav;
    }


    private SimpleMailMessage getSimpleMailMessage(Klant klant, ConfirmationToken confirmationToken) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(klant.getEmailadres());
        mailMessage.setSubject("Voltooi het opnieuw instellen van het wachtwoord!");
        mailMessage.setFrom("noreplyibvh@gmail.com");
        mailMessage.setText("Klik hier om het wachtwoordherstelproces te voltooien: "
                + "http://localhost:8080/confirm-reset?token=" + confirmationToken.getConfirmationToken());
        return mailMessage;
    }

    /**
     * om het wachrwoord opnieuw te instellen
     * @param modelAndView
     * @param confirmationToken
     * @return
     */
    @RequestMapping(value = "/confirm-reset", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView validateResetToken(ModelAndView modelAndView, @RequestParam("token") String confirmationToken) {
        ConfirmationToken token = confirmationTokenService.getByConfirmationToken(confirmationToken);
        if (token != null) {
            Klant klant = klantService.findKlantByGebruikersnaam(token.getKlant().getGebruikersnaam());
            klant.setEnabled(true);
            klantService.saveKlant(klant);
            modelAndView.addObject("klant", klant);
            modelAndView.addObject("gebruikersnaam", klant.getGebruikersnaam());
            modelAndView.setViewName("resetWachtwoord");
        } else {
            modelAndView.addObject("message", ERROR_LINK_MESSAGE );
            modelAndView.setViewName("resetWachtwoord");
        }
        return modelAndView;
    }

    /**
     * Ontvang het token via de link die via e-mail is verzonden en geef het formulier weer om het wachtwoord opnieuw in te stellen
     */
    @RequestMapping(value = "/reset-wachtwoord", method = RequestMethod.POST)
    public ModelAndView resetKlantPassword(ModelAndView modelAndView, Klant klant) {
        if (klant.getGebruikersnaam() != null) {
            Klant tokenKlant = klantService.findKlantByGebruikersnaam(klant.getGebruikersnaam());
            tokenKlant.setEnabled(true);
            tokenKlant.setWachtwoord(getGehashedWachtwoord(klant.getWachtwoord(), SALT));
            klantService.updateKlant(tokenKlant);
            modelAndView.addObject("message", SUCCESS_MESSAGE );
            modelAndView.setViewName("succesResetWachtwoord");
        } else {
            modelAndView.addObject("message", ERROR_LINK_MESSAGE);
            modelAndView.setViewName("succesResetWachtwoord");
        }
        return modelAndView;
    }


}
