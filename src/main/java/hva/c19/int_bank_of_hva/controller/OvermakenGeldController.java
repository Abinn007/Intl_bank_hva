package hva.c19.int_bank_of_hva.controller;

import hva.c19.int_bank_of_hva.model.Rekening;
import hva.c19.int_bank_of_hva.model.Transactie;
import hva.c19.int_bank_of_hva.service.RekeningService;
import hva.c19.int_bank_of_hva.service.TransactieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("rekeningnummer")
public class OvermakenGeldController {
    Rekening rekeningDebet;
    String rekeningNrDebet;

    @Autowired
    private final TransactieService transactieService;

    @Autowired
    private final RekeningService rekeningService;

    // Constructors
    public OvermakenGeldController(TransactieService transactieService, RekeningService rekeningService) {
        super();
        this.transactieService = transactieService;
        this.rekeningService = rekeningService;
    }

    // De methode om de juiste objecten mee te geven aan de view.
    @GetMapping("/overmakenGeld")
    public String overmakenGeldHandler(@RequestParam("rekeningNr") String rekeningnummer, Model model) {
        Transactie transactie = new Transactie();
        rekeningDebet = rekeningService.getRekeningByRekeningNr(rekeningnummer);
        rekeningNrDebet = rekeningnummer;
        double saldo = rekeningDebet.getSaldo();
        model.addAttribute("geldTransactieBean", transactie);
        model.addAttribute("saldo", saldo);
        return "overmakenGeld";
    }

    // De methode die uitgevoerd wordt als men het overmaken bevestigd.
    @PostMapping("/geldTransactie")
    public String geldTransactieHandler(@ModelAttribute("geldTransactieBean") Transactie transactie) {
        transactie.setRekening(rekeningDebet);
        transactie.setRekeningNrDebet(rekeningDebet.getRekeningnummer());

        if(transactieService.bestaandRekeningNr(transactie.getRekeningNrCredit())){
            transactieService.maakGeldOver(transactie);
            return "overmakenGeldGelukt";
        } else return "overmakenGeldError";
    }
}
