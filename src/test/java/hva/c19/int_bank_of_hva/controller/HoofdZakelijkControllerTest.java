package hva.c19.int_bank_of_hva.controller;

import hva.c19.int_bank_of_hva.model.ZakelijkRekening;
import hva.c19.int_bank_of_hva.service.RekeningService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class HoofdZakelijkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RekeningService rekeningService;

    public HoofdZakelijkControllerTest() {
        super();

    }

    @Test
    public void handlerLijstTest() {
        rekeningService.saveRekening (new ZakelijkRekening("12456", 8000, "Blade1", 1254715, "14578B03", "A1"));
        rekeningService.saveRekening (new ZakelijkRekening("20456", 700, "Blade2", 1254785, "14578B01", "A2"));
        rekeningService.saveRekening (new ZakelijkRekening("14456", 4500, "Blade3", 1254795, "14578B02", "A3"));
        rekeningService.saveRekening (new ZakelijkRekening("10456", 5500, "Blade4", 1254755, "14579B01", "A4"));
        rekeningService.saveRekening (new ZakelijkRekening("15456", 6500, "Blade5", 1254705, "14568B01", "A5"));
        rekeningService.saveRekening (new ZakelijkRekening("16456", 7500, "Blade6", 1254724, "14178B01", "A6"));
        rekeningService.saveRekening (new ZakelijkRekening("14456", 8500, "Blade3", 1254795, "14578B02", "A3"));
        rekeningService.saveRekening (new ZakelijkRekening("18456", 1500, "Blade8", 1254700, "14078B01", "A7"));
        rekeningService.saveRekening (new ZakelijkRekening("12486", 500, "Blade9", 1254747, "14570B01", "A8"));
        rekeningService.saveRekening (new ZakelijkRekening("12756", 1000, "Blade10", 1254500, "12578B01", "A9"));
        rekeningService.saveRekening (new ZakelijkRekening("12456", 2000, "Blade1", 1254715, "14578B03", "A1"));
        rekeningService.saveRekening (new ZakelijkRekening("12956", 3500, "Blade11", 1254385, "10578B01", "A11"));
        rekeningService.saveRekening (new ZakelijkRekening("12056", 4500, "Blade12", 1252785, "11178B01", "A12"));
        rekeningService.saveRekening (new ZakelijkRekening("12856", 3100, "Blade13", 1251785, "11278B01", "A13"));
        rekeningService.saveRekening (new ZakelijkRekening("10856", 300, "Blade14", 1251705, "11270B01", "A14"));


        ZakelijkRekening zakRekening1 = new ZakelijkRekening("14456", 13000, "Blade3", 1254795, "14578B02", "A3");
        ZakelijkRekening zakRekening2 = new ZakelijkRekening("12456", 10000, "Blade1", 1254715, "14578B03", "A1");
        ZakelijkRekening zakRekening3 = new ZakelijkRekening("16456", 7500, "Blade6", 1254724, "14178B01", "A6");
        ZakelijkRekening zakRekening4 = new ZakelijkRekening("15456", 6500, "Blade5", 1254705, "14568B01", "A5");
        ZakelijkRekening zakRekening5 = new ZakelijkRekening("10456", 5500, "Blade4", 1254755, "14579B01", "A4");
        ZakelijkRekening zakRekening6 = new ZakelijkRekening("12056", 4500, "Blade12", 1252785, "11178B01", "A12");
        ZakelijkRekening zakRekening7 = new ZakelijkRekening("12956", 3500, "Blade12", 1254385, "10578B01", "A11");
        ZakelijkRekening zakRekening8 = new ZakelijkRekening("12856", 3100, "Blade12", 1251785, "11278B01", "A13");
        ZakelijkRekening zakRekening9 = new ZakelijkRekening("18456", 1500, "Blade8", 1254700, "14078B01", "A7");
        ZakelijkRekening zakRekening10 = new ZakelijkRekening("12756", 1000, "Blade10", 1254500, "12578B01", "A9");

        List<ZakelijkRekening> topTienZakRekenning = new ArrayList<>();
        topTienZakRekenning.add(0, zakRekening1);
        topTienZakRekenning.add(1, zakRekening2);
        topTienZakRekenning.add(2, zakRekening3);
        topTienZakRekenning.add(3, zakRekening4);
        topTienZakRekenning.add(4, zakRekening5);
        topTienZakRekenning.add(5, zakRekening6);
        topTienZakRekenning.add(6, zakRekening7);
        topTienZakRekenning.add(7, zakRekening8);
        topTienZakRekenning.add(8, zakRekening9);
        topTienZakRekenning.add(9, zakRekening10);

        Mockito.when(rekeningService.tienBedrijfvenMetHoogsteSaldo()).thenReturn(topTienZakRekenning);

        assertEquals(topTienZakRekenning.get(0),rekeningService.tienBedrijfvenMetHoogsteSaldo().get(0));
        assertEquals(topTienZakRekenning.get(9),rekeningService.tienBedrijfvenMetHoogsteSaldo().get(9));

        assertEquals(10,rekeningService.tienBedrijfvenMetHoogsteSaldo().size());

    }

    @Test
    public void handlerViewTest() {
        try {
            MockHttpServletRequestBuilder getRequest =
                    MockMvcRequestBuilders.get("/bedrijven_met_hoogste_saldo");
            ResultActions result = mockMvc.perform(getRequest);
            result.andDo(print());
            result.andExpect(status().isOk());
            result.andExpect(view().name("bedrijvenMetHoogsteSaldo"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


