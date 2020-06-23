package hva.c19.int_bank_of_hva.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

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

    @RequestMapping("/logout")
    public String logout() {
        return "loginKlant";
    }

    @GetMapping("plogin")
    public String handleParticulierLogin() {
        return "loginKlant";
    }

    @GetMapping("/mlogin")
    public String handleMedewerkerLogin() {
        return "loginMedewerker";
    }

    @GetMapping("openenRekening")
    public String handleOpenenRekening() {
        return "openenRekening";
    }


}
