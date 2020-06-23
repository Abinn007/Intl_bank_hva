package hva.c19.int_bank_of_hva.controller;

import hva.c19.int_bank_of_hva.model.Klant;
import hva.c19.int_bank_of_hva.model.Rekening;
import hva.c19.int_bank_of_hva.model.ZakelijkRekening;
import hva.c19.int_bank_of_hva.service.KlantService;
import hva.c19.int_bank_of_hva.service.RekeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("gebruikersnaam")
public class OpenenRekeningZakelijkController {
    private static final double BEGIN_SALDO = 0.0;
    private static final String CONSTANT_DEEL_REKENINGNUMMER = "NL35IBVH";
    private static final String CIJFER_DEEL_REKENINGNUMMER = "2000000000";
    private static final int CIJFER_DEEL_SUBSTRING = 8;

    @Autowired
    private final KlantService klantService;
    @Autowired
    private final RekeningService rekeningService;

    //Constructors
    public OpenenRekeningZakelijkController() {
        this(null, null);
    }

    public OpenenRekeningZakelijkController(KlantService klantService, RekeningService rekeningService) {
        this.klantService = klantService;
        this.rekeningService = rekeningService;
    }

    /**
     * Om een pagina te gaan waar een klant extra gegevens kan invullen
     * om een zakelijk rekening te openen.
     *
     * @return mav
     */
    @PostMapping("/rekening_zakelijk")
    public ModelAndView handelRekeningZakelijk(Model model) {
        ModelAndView mav = new ModelAndView("openenRekeningZakelijk2");
        String gebruikersnaam = (String) model.getAttribute("gebruikersnaam");
        Klant klant = klantService.findKlantByGebruikersnaam(gebruikersnaam);
        mav.addObject("klant", klant);
        Rekening zakelijkRekening = new ZakelijkRekening();
        mav.addObject("rekening", zakelijkRekening);
        return mav;
    }

    /**
     * Om een nieuwe zakelijke rekening te openen en op te slaan in de database.
     *
     * @param bedrijfsnaam
     * @param kvkNummer
     * @param btwNummer
     * @param sector
     * @param model
     * @return mav
     */
    @PostMapping("/opslaan_rekening_zakelijk")
    public ModelAndView handelOpslaanRekeningZakelijk(@RequestParam String bedrijfsnaam, @RequestParam int kvkNummer,
                                                      @RequestParam String btwNummer, @RequestParam String sector, Model model) {
        ModelAndView mav = new ModelAndView("openenRekeningBevestiging");
        String gebruikersnaam = (String) model.getAttribute("gebruikersnaam");
        Klant klant = klantService.findKlantByGebruikersnaam(gebruikersnaam);
        mav.addObject("klant", klant);
        addAttributeMAV(bedrijfsnaam, kvkNummer, btwNummer, sector, mav);
        String rekeningnummer = nieuweRekeningnummer();
        Rekening zakelijkRekening = new ZakelijkRekening(rekeningnummer, BEGIN_SALDO, bedrijfsnaam, kvkNummer, btwNummer, sector);
        mav.addObject("rekeningnummer", zakelijkRekening.getRekeningnummer());
        zakelijkRekening.klantToevoegen(klant);
        rekeningService.saveRekening(zakelijkRekening);
        return mav;
    }

    /**
     * Om attributes toe te voegen in ModelAndView.
     * @param bedrijfsnaam
     * @param kvkNummer
     * @param btwNummer
     * @param sector
     * @param mav
     */

    private void addAttributeMAV(@RequestParam String bedrijfsnaam, @RequestParam int kvkNummer, @RequestParam String btwNummer, @RequestParam String sector, ModelAndView mav) {
        mav.addObject("bedrijfsnaam", bedrijfsnaam);
        mav.addObject("kvk", kvkNummer);
        mav.addObject("btw", btwNummer);
        mav.addObject("sector", sector);
    }

    /**
     * Om een nieuwe zakelijk rekeningnummer te creëren.
     *
     * @return nieuwe rekeningnummer.
     */
    private String nieuweRekeningnummer() {
        String rekeningnummer;
        if (rekeningService.getHoogsteZakelijkRekeningnummer() == null) {
            rekeningnummer = CONSTANT_DEEL_REKENINGNUMMER + CIJFER_DEEL_REKENINGNUMMER;
        } else {
            long laatsteGebruikteRekeningnummer = Long.parseLong(rekeningService.
                    getHoogsteZakelijkRekeningnummer().substring(CIJFER_DEEL_SUBSTRING));
            rekeningnummer = CONSTANT_DEEL_REKENINGNUMMER + (laatsteGebruikteRekeningnummer + 1);
        }
        return rekeningnummer;
    }
}
