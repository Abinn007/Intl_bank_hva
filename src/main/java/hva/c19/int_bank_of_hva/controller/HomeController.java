package hva.c19.int_bank_of_hva.controller;

import hva.c19.int_bank_of_hva.utilities.KlantBackingBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    // CONSTRUCTOR
    public HomeController() {
        super();
    }

    @GetMapping("/")
    public String handleFirst() {
        return "first";
    }

    @GetMapping("/home")
    public String handleHome() {
        return "home";
    }

    /**
     * om klant te uitloggin en session dicht te doen
     * @return loginKlant.html
     */
    @RequestMapping("/logout")
    public ModelAndView logoutKlant(HttpServletRequest request, SessionStatus session, Model model) {
        model.asMap().clear();
        session.setComplete();
        request.getSession().invalidate();
        System.out.println("Session is closed.");
        return new ModelAndView("loginKlant");
    }

    /**
     * om medewerker te uitloggin en session dicht te doen
     * @return loginKlant.html
     */
    @RequestMapping("/logoutMdw")
    public ModelAndView logoutMedewerker(HttpServletRequest request, SessionStatus session, Model model) {
        model.asMap().clear();
        session.setComplete();
        request.getSession().invalidate();
        System.out.println("Session is closed.");
        return new ModelAndView("loginMedewerker");
    }
    /**
     * om klant te inloggen
     * @return loginKlant.html
     */
    @GetMapping("klntlogin")
    public String handleParticulierLogin() {
        return "loginKlant";
    }

    /**
     * om medewerker te login
     * @return loginMedewerker.html
     */
    @GetMapping("/mdwrlogin")
    public String handleMedewerkerLogin() {
        return "loginMedewerker";
    }

    /**
     * Om een nieuwe klant te worden
     *
     * @return wordenKlant.html
     */
    @GetMapping("/nieuweKlant")
    public ModelAndView handelNieuwKlant() {
        ModelAndView mav = new ModelAndView("wordenKlant");
        KlantBackingBean klantBackingBean = new KlantBackingBean();
        mav.addObject("klantBean", klantBackingBean);
        return mav;
    }
}
