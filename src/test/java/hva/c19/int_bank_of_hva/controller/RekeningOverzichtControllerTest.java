package hva.c19.int_bank_of_hva.controller;

import hva.c19.int_bank_of_hva.service.KlantService;
import hva.c19.int_bank_of_hva.service.RekeningService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

class RekeningOverzichtControllerTest {

    @MockBean
    KlantService klantService = Mockito.mock(KlantService.class);
    RekeningService rekeningService = Mockito.mock(RekeningService.class);

    public RekeningOverzichtControllerTest() {
        super();
    }

    @Test
    public void test() {

    }
}