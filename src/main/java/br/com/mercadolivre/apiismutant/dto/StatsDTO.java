package br.com.mercadolivre.apiismutant.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatsDTO {
    private Long count_mutant_dna;
    private Long count_human_dna;
    private BigDecimal ratio;
}
