package hva.c19.int_bank_of_hva.controller;

import hva.c19.int_bank_of_hva.model.ConfirmationToken;
import hva.c19.int_bank_of_hva.model.Klant;
import hva.c19.int_bank_of_hva.repositories.ConfirmationTokenRepository;
import hva.c19.int_bank_of_hva.service.ConfirmationTokenService;
import hva.c19.int_bank_of_hva.service.EmailSenderService;
import hva.c19.int_bank_of_hva.service.KlantService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Controller
@SessionAttributes("gebruikersnaam")
public class LoginKlantController {
    private final KlantService klantService;
    private static final String SALT = "QxLUF1bgIAdeQX";
    private final EmailSenderService emailSenderService;
    private final ConfirmationTokenService confirmationTokenService;

    public LoginKlantController(KlantService klantService, EmailSenderService emailSenderService, ConfirmationTokenService confirmationTokenService) {
        super();
        this.klantService = klantService;
        this.emailSenderService = emailSenderService;
        this.confirmationTokenService = confirmationTokenService;
    }

    /**
     * Om in te loggen naar welkom pagina
     * @param gebruikersnaam
     * @param wachtwoord
     * @return
     */
    @GetMapping("klantInloggen")
    public ModelAndView handleKlantInloggen (@RequestParam String gebruikersnaam, @RequestParam String wachtwoord) {
        ModelAndView mav = null;
        String veiligeWachtwoord = getHashedWachtwoord(wachtwoord,SALT);
        Klant klant = klantService.findKlantByGebruikersnaam(gebruikersnaam);
        System.out.println(klant);
        if (klant !=  null && klant.getGebruikersnaam().equals(gebruikersnaam) && klant.getWachtwoord().equals(veiligeWachtwoord)){
            mav =new ModelAndView("welkom");
            mav.addObject("klant", klant);
            mav.addObject("gebruikersnaam", klant.getGebruikersnaam());
        } else {
            mav = new ModelAndView("loginKlant");
            mav.addObject("message", "<div style= 'margin-bottom: 5px; border: 2px solid red; color: #0069c0; font-size: 17px;" +
                    " border-radius: 5px;' ><i class=\"fa fa-exclamation-circle\" style=\"color: red;\" aria-hidden=\"true\"></i>  Je inloggegevens kloppen niet, controleer de combinatie van je gebruikersnaam en wachtwoor.️</div>");
        }
        return mav;
    }

    /**
     * Om een gehashed wachtwoord te valideren
     * @param wachtwoord
     * @param salt
     * @return
     */
    public String getHashedWachtwoord(String wachtwoord, String salt){
        String hashedWachtwoord = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(wachtwoord.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            hashedWachtwoord = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashedWachtwoord;
    }

    /**
     * Display the forgot password page and form
     */
    @GetMapping("/passwordVergeten")
    public String displayResetPassword() {
        return "forgotPassword";
    }

    /**
     * Receive email of the user, create token and send it via email to the user
     */
    @RequestMapping(value = "/forgot-password", method = RequestMethod.POST)
    public ModelAndView forgotKlantPassword(@RequestParam String gebruikersnaam, ModelAndView mav, Model model) {
        Klant klant = klantService.findKlantByGebruikersnaam(gebruikersnaam);
        if (klant != null) {
            ConfirmationToken confirmationToken = new ConfirmationToken(klant);
            confirmationTokenService.saveConfirmationToken(confirmationToken);
            SimpleMailMessage mailMessage = getSimpleMailMessage(klant, confirmationToken);
            emailSenderService.sendEmail(mailMessage);
            mav.addObject("message", "Request to reset password received. Check your inbox for the reset link.");
            mav.setViewName("successResetPassword");
        } else {
            mav.setViewName("forgotPassword");
            mav.addObject("message", "<i class=\"fa fa-exclamation-circle\" style=\"color: red; font-size:20px;\" aria-hidden=\"true\"></i> Er is geen gebruikersnaam met " + gebruikersnaam + " .");
        }
        return mav;
    }

    private SimpleMailMessage getSimpleMailMessage(Klant klant, ConfirmationToken confirmationToken) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(klant.getEmailadres());
        mailMessage.setSubject("Complete Password Reset!");
        mailMessage.setFrom("noreplyibvh@gmail.com");
        mailMessage.setText("To complete the password reset process, please click here: "
                + "http://localhost:8080/confirm-reset?token=" + confirmationToken.getConfirmationToken());
        return mailMessage;
    }


    @RequestMapping(value = "/confirm-reset", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView validateResetToken(ModelAndView modelAndView, @RequestParam("token") String confirmationToken) {
        ConfirmationToken token = confirmationTokenService.getByConfirmationToken(confirmationToken);
        if (token != null) {
            Klant klant = klantService.findKlantByGebruikersnaam(token.getKlant().getGebruikersnaam());
            klant.setEnabled(true);
            klantService.saveKlant(klant);
            modelAndView.addObject("klant", klant);
            modelAndView.addObject("gebruikersnaam", klant.getGebruikersnaam());
            modelAndView.setViewName("resetPassword");
        } else {
            modelAndView.addObject("message", "<i class=\"fa fa-exclamation-circle\" style=\"color: red;\" aria-hidden=\"true\"></i> The link is invalid or broken!");
            modelAndView.setViewName("resetPassword");
        }
        return modelAndView;
    }

    /**
     * Receive the token from the link sent via email and display form to reset password
     */
    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
    public ModelAndView resetKlantPassword(ModelAndView modelAndView, Klant klant) {
        if (klant.getGebruikersnaam() != null) {
            // Gebruik het gebruikersnaam om klant te vinden
            Klant tokenKlant = klantService.findKlantByGebruikersnaam(klant.getGebruikersnaam());
            tokenKlant.setEnabled(true);
            tokenKlant.setWachtwoord(getHashedWachtwoord(klant.getWachtwoord(),SALT));
            klantService.updateKlant(tokenKlant);
            modelAndView.addObject("message", "Password successfully reset. You can now log in with the new credentials.");
            modelAndView.setViewName("successResetPassword");
        } else {
            modelAndView.addObject("message", "<i class=\"fa fa-exclamation-circle\" style=\"color: red;\" aria-hidden=\"true\"></i> The link is invalid or broken!");
            modelAndView.setViewName("successResetPassword");
        }
        return modelAndView;
    }


}
