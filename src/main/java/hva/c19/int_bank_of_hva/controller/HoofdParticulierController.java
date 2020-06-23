package hva.c19.int_bank_of_hva.controller;

import hva.c19.int_bank_of_hva.model.Klant;
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
    @Autowired
    private RekeningService rekeningService;

    public HoofdParticulierController(KlantService klantService, RekeningService rekeningService) {
        this.klantService = klantService;
        this.rekeningService = rekeningService;
    }

    @GetMapping("/hoofdParticulier")
    public String handelHoofdParticulier(){
        return "hoofdParticulieren";
    }

    @GetMapping("/hoofdParticulierenHoogsteSaldo")
    public String TopTienKlantenHandler(Model model) {
        List<ParticulierRekening> topTienKlantenLijst = rekeningService.klantenMetHoogsteParticulierRekeningSaldo();
        List<Klant> klantLijst = klantService.klantenMetHoogsteParticulierRekeningSaldoKlant();
        System.out.println(klantLijst);
//        System.out.println(topTienKlantenLijst.get(1).getRekeningId());
//        System.out.println(topTienKlantenLijst.get(1).toString());
//        System.out.println(topTienKlantenLijst.get(1).);
//      topTienKlantenLijst.get(1).getRekeningnummer();
//      [[Voorletters Achternaam] Rekeningnummer Saldo,[Voorletters Achternaam] Rekeningnummer Saldo,[Voorletters Achternaam]Rekiningnummer Saldo,[Voorletters]Rekiningnummer Saldo,]

        model.addAttribute("topTienKlantenLijst", topTienKlantenLijst);
        model.addAttribute("klantLijst", klantLijst);

        return "hoofdParticulierenHoogsteSaldo";
    }
}
