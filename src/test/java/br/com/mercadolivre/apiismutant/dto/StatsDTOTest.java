package br.com.mercadolivre.apiismutant.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class StatsDTOTest {

    @Test
    public void StatsDTO_construct_test() {
        StatsDTO statsDTO = new StatsDTO(1L,1L,new BigDecimal(10));

        Assertions.assertNotNull(statsDTO);
    }

    @Test
    public void StatsDTO_getterSetter_test() {
        StatsDTO statsDTO = new StatsDTO();

        statsDTO.setCount_human_dna(1L);
        statsDTO.setCount_mutant_dna(1L);
        statsDTO.setRatio(new BigDecimal(10));

        Assertions.assertNotNull(statsDTO.getCount_human_dna());
        Assertions.assertNotNull(statsDTO.getCount_mutant_dna());
        Assertions.assertNotNull(statsDTO.getRatio());
    }
}