package hva.c19.int_bank_of_hva.controller;

import hva.c19.int_bank_of_hva.model.Rekening;
import hva.c19.int_bank_of_hva.model.Transactie;
import hva.c19.int_bank_of_hva.service.RekeningService;
import hva.c19.int_bank_of_hva.service.TransactieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("gebruikersnaam")
public class InzienTransactiesEnSaldoController {

    @Autowired
    private final TransactieService transactieService;

    @Autowired
    private final RekeningService rekeningService;

    public InzienTransactiesEnSaldoController(TransactieService transactieService, RekeningService rekeningService) {
        this.transactieService = transactieService;
        this.rekeningService = rekeningService;
    }

    public InzienTransactiesEnSaldoController() {
        this(null, null);
    }

    @GetMapping("inzienTransactiesEnSaldo")
    public String handleInzienTransactiesEnSaldo(@RequestParam("rekeningNr") String rekeningNr, Model model) {
        String gebruikersnaam = (String) model.getAttribute("gebruikersnaam");
        model.addAttribute("rekeningnummer", rekeningNr);
        Rekening rekening = rekeningService.getRekeningByRekeningnummer(rekeningNr);
        List<Transactie> transactieList = transactieService.transactieList(rekeningNr);
        if (!transactieList.isEmpty()) {
            model.addAttribute("transactieList", transactieList);
            model.addAttribute("saldo", rekening.getSaldo());
            model.addAttribute("gebruikersnaam", gebruikersnaam);
            return "inzienTransactiesEnSaldo";
        } else {
            model.addAttribute("gebruikersnaam", gebruikersnaam);
            return "inzienTransactiesError";
        }
    }
}
