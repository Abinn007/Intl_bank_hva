package hva.c19.int_bank_of_hva.controller;

import hva.c19.int_bank_of_hva.service.KlantService;
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
    @Autowired
    private final KlantService klantService;

    public HoofdZakelijkController() {
        this(null, null, null);
    }

    public HoofdZakelijkController(TransactieService transactieService, RekeningService rekeningService, KlantService klantService) {
        this.transactieService = transactieService;
        this.rekeningService = rekeningService;
        this.klantService = klantService;
    }

    /**
     * Om een lijst te tonen van top 10 bedrijven met hoogste saldo
     *
     * @param model model
     * @return bedrijvenMetHoogsteSaldo.html
     */
    @GetMapping("/bedrijven_met_hoogste_saldo")
    public String hoogsteSaldoHandler(Model model) {
        model.addAttribute("bedrijfsLijst", rekeningService.tienBedrijfvenMetHoogsteSaldo());
        return "bedrijvenMetHoogsteSaldo";
    }

    /**
     * Om de bedrijfsgegevens te tonen.
     *
     * @param id    rekeningId
     * @param model model
     * @return bedrijfsgegevens.html
     */
    @GetMapping("/bedrijfsgegevens")
    public String bedrijfgegevensHandler(@RequestParam("rekeningId") int id, Model model) {
        model.addAttribute("rekening", rekeningService.findRekeningById(id));
        return "bedrijfsgegevens";
    }

    /**
     * Om een lijst te tonen van top 10 bedrijven met hoogste saldo per sector.
     *
     * @param model
     * @return bedrijvenGemiddeldeSaldoPerSector.html
     */
    @GetMapping("/gemiddelde_saldo_per_sector")
    public String gemiddeldeSaldoHandler(Model model) {
        model.addAttribute("gemiddeldeSaldoList", rekeningService.gemiddeldeSaldoPerSector());
        return "bedrijvenGemiddeldeSaldoPerSector";
    }

    /**
     * Om een lijst tonen van top 10 meest actieve bedrijven
     * op basis van totaal transacties.
     *
     * @param model
     * @return bedrijvenMeestActieve.html
     */
    @GetMapping("/meest_actieve_bedrijven")
    public String meesteActiveKlantenHandler(Model model) {
        Map<String, Integer> totaalTransactie = transactieService.aantalHoogsteTransacties(AANTALTRANSACTIES);
        model.addAttribute("totaalTransactie", totaalTransactie);
        return "bedrijvenMeestActieve";
    }

    /**
     * Om de bedrijfsgegevens te tonen.
     *
     * @param naam  bedrijfsnaam
     * @param model model
     * @return bedrijfsgegevens.html
     */
    @GetMapping("/gegevens")
    public String bedrijfgegevensHandler(@RequestParam("bedrijfsnaam") String naam, Model model) {
        model.addAttribute("rekening", rekeningService.findRekeningByBedrijfsnaam(naam));
        return "bedrijfsgegevens";
    }

    /**
     * Om de klantgegevens te tonen.
     *
     * @param id    rekeningId
     * @param model model
     * @return bedrijfsKlantgegevens.html
     */
    @GetMapping("/klantgegevens")
    public String klantgegevensHandler(@RequestParam("rekeningId") int id, Model model) {
        model.addAttribute("klanten", klantService.findAllByRekeningId(id));
        return "bedrijfsKlantgegevens";

    }
}

