package hva.c19.int_bank_of_hva.controller;

import hva.c19.int_bank_of_hva.model.Klant;
import hva.c19.int_bank_of_hva.model.ZakelijkRekening;
import hva.c19.int_bank_of_hva.service.KlantService;
import hva.c19.int_bank_of_hva.service.ZakelijkRekeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("gebruikersnaam")
public class BeheerRekeningController {

    @Autowired
    private final KlantService klantService;

    @Autowired
    private final ZakelijkRekeningService zakelijkRekeningService;

    public BeheerRekeningController(KlantService klantService, ZakelijkRekeningService zakelijkRekeningService) {
        this.klantService = klantService;
        this.zakelijkRekeningService = zakelijkRekeningService;
    }

    public BeheerRekeningController() {this(null, null);}

}
