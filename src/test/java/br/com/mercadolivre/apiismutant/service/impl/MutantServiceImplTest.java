package br.com.mercadolivre.apiismutant.service.impl;

import br.com.mercadolivre.apiismutant.service.StatsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MutantServiceImplTest {

    @InjectMocks
    private MutantServiceImpl mutantService;

    @Mock
    private StatsService statsService;

    @Test
    public void MutantServiceImpl_isMutant_true_test() {
        String [] dna = {"CTGAGA", "CTGAGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"};

        boolean isMutant = mutantService.isMutant(dna);

        Assertions.assertTrue(isMutant);
    }

    @Test
    public void MutantServiceImpl_isMutant_exception_test() {
        String [] dna = {};

        Exception exception = assertThrows(Exception.class, () -> {
            mutantService.isMutant(dna);
        },"error");

        Assertions.assertTrue(exception.getMessage().contains("Human are empty!"));
    }
}