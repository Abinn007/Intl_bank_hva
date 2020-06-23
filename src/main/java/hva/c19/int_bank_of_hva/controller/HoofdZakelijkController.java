package hva.c19.int_bank_of_hva.controller;

import hva.c19.int_bank_of_hva.model.ZakelijkRekening;
import hva.c19.int_bank_of_hva.service.RekeningService;
import hva.c19.int_bank_of_hva.service.TransactieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class HoofdZakelijkController {
    private static final int AANTALTRANSACTIES = 10;

    @Autowired
    private final TransactieService transactieService;
    @Autowired
    private final RekeningService rekeningService;

    public HoofdZakelijkController(TransactieService transactieService, RekeningService rekeningService) {
        this.transactieService = transactieService;
        this.rekeningService = rekeningService;
    }

    @GetMapping("/bedrijven_met_hoogste_saldo")
    public String hoogsteSaldoHandler(Model model) {
        List<ZakelijkRekening> rekening = rekeningService.tienBedrijfvenMetHoogsteSaldo();
        model.addAttribute("bedrijfsLijst", rekeningService.tienBedrijfvenMetHoogsteSaldo());
        return "bedrijvenMetHoogsteSaldo";
    }

    @GetMapping("/bedrijfsgegevens")
    public String bedrijfgegevensHandler(@RequestParam("rekeningId") Integer id, Model model) {
        model.addAttribute("rekening", rekeningService.findRekeningById(id));
        return "bedrijfsgegevens";
    }

    @GetMapping("/gemiddelde_saldo_per_sector")
    public String gemiddeldeSaldoHandler(Model model) {
        model.addAttribute("gemiddeldeSaldoList", rekeningService.gemiddeldeSaldoPerSector());
        return "bedrijvenGemiddeldeSaldoPerSector";
    }

    @GetMapping("/meest_actieve_bedrijven")
    public String meesteActiveKlantenHandler(Model model) {
        Map<String, Integer> totaalTransactie = transactieService.aantalHoogsteTransacties(AANTALTRANSACTIES);
        model.addAttribute("totaalTransactie", totaalTransactie);
        return "bedrijvenMeestActieve";
    }

    @GetMapping("/gegevens")
    public String bedrijfgegevensHandler(@RequestParam("bedrijfsnaam") String naam, Model model) {
        model.addAttribute("rekening", rekeningService.findRekeningByBedrijfsnaam(naam));
        return "bedrijfsgegevens";
    }
}
