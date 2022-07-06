package br.com.mercadolivre.apiismutant.service;

import br.com.mercadolivre.apiismutant.domain.Stats;
import br.com.mercadolivre.apiismutant.dto.StatsDTO;

public interface StatsService {
    Stats save(Stats stats);
    StatsDTO stats();
}
