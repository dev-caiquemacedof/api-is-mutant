package br.com.mercadolivre.apiismutant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class StatsDTO {
    private Long count_mutant_dna;
    private Long count_human_dna;
    private BigDecimal ratio;
}
