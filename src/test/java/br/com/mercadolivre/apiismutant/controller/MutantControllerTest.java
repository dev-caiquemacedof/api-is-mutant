package br.com.mercadolivre.apiismutant.controller;

import br.com.mercadolivre.apiismutant.dto.Human;
import br.com.mercadolivre.apiismutant.dto.StatsDTO;
import br.com.mercadolivre.apiismutant.service.MutantService;
import br.com.mercadolivre.apiismutant.service.StatsService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MutantControllerTest {

    @InjectMocks
    private MutantController mutantController;

    @Mock
    private MutantService analyzeService;

    @Mock
    private StatsService statsService;

    @Test
    public void isMutant_true_test() {
        String [] dna = {"CTGAGA", "CTGAGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"};

        when(analyzeService.isMutant(dna)).thenReturn(true);

        ResponseEntity<Void> mutantResponse = mutantController.isMutant(new Human(dna));

        Assertions.assertEquals(mutantResponse.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void isMutant_false_test() {
        String [] dna = {"CTGAGA", "CTGAGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"};

        when(analyzeService.isMutant(dna)).thenReturn(false);

        ResponseEntity<Void> mutantResponse = mutantController.isMutant(new Human(dna));

        Assertions.assertEquals(mutantResponse.getStatusCode(), HttpStatus.FORBIDDEN);
    }
    @Test
    public void getStats_test() {
        ResponseEntity<StatsDTO> statsResponse = mutantController.getStats();

        Assertions.assertEquals(statsResponse.getStatusCode(), HttpStatus.OK);
    }
}