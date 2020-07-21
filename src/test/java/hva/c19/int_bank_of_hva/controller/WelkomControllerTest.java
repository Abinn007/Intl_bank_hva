package hva.c19.int_bank_of_hva.controller;

import hva.c19.int_bank_of_hva.model.Klant;
import hva.c19.int_bank_of_hva.service.KlantService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WelkomController.class)
class WelkomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private KlantService klantService;
    private hva.c19.int_bank_of_hva.model.Klant Klant;

    public WelkomControllerTest() {
        super();
    }

    @Test
    void welkomHandler() {
        //Mockito.when(klantService.findKlantByGebruikersnaam("Berend")).thenReturn(Klant)
        try {
            MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.get("/welkom");
            ResultActions result = mockMvc.perform(getRequest);
            result.andDo(print()).andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}