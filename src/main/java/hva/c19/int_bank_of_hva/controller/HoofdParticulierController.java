package hva.c19.int_bank_of_hva.controller;

import hva.c19.int_bank_of_hva.model.Klant;
import hva.c19.int_bank_of_hva.model.KlantRekening;
import hva.c19.int_bank_of_hva.model.ParticulierRekening;
import hva.c19.int_bank_of_hva.service.KlantService;
import hva.c19.int_bank_of_hva.service.RekeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HoofdParticulierController {

    @Autowired
    private KlantService klantService;

    public HoofdParticulierController(KlantService klantService, RekeningService rekeningService) {
        this.klantService = klantService;
    }

    @GetMapping("/hoofdParticulier")
    public String handelHoofdParticulier(){
        return "hoofdParticulieren";
    }

    @GetMapping("/hoofdParticulierenHoogsteSaldo")
    public String TopTienKlantenHandler(Model model) {
        List<KlantRekening> klantLijst = klantService.klantenMetHoogsteParticulierRekeningSaldoKlant();
        System.out.println(klantLijst);
        model.addAttribute("klantLijst", klantLijst);
        return "hoofdParticulierenHoogsteSaldo";
    }
}
