package br.com.mercadolivre.apiismutant.service.impl;

import br.com.mercadolivre.apiismutant.domain.Origin;
import br.com.mercadolivre.apiismutant.domain.Stats;
import br.com.mercadolivre.apiismutant.dto.StatsDTO;
import br.com.mercadolivre.apiismutant.repository.StatsRepository;
import br.com.mercadolivre.apiismutant.service.StatsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static br.com.mercadolivre.apiismutant.util.Util.PERCENT;
import static java.math.BigDecimal.valueOf;

@Service
public class StatsServiceImpl implements StatsService {

    private static final Logger logger = LoggerFactory.getLogger(StatsService.class);
    private final StatsRepository repository;

    @Autowired
    public StatsServiceImpl(StatsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Stats save(Stats stats) {
        return repository.save(stats);
    }

    @Override
    public StatsDTO stats() {
        logger.info("Calculating ratio...");
        long mutants = repository.countByOrigin(Origin.MUTANT);
        long humans = repository.countByOrigin(Origin.HUMAN);
        return new StatsDTO(mutants, humans, calculateRatio(valueOf(humans), valueOf(mutants)));
    }

    private BigDecimal calculateRatio(BigDecimal humans, BigDecimal mutants) {
        logger.info("Calculating ratio mutants to hummans: {}:{}", mutants, humans);

        if (mutants.equals(BigDecimal.ZERO)) {
            logger.info("Mutants not found!");
            return BigDecimal.ZERO;
        }

        BigDecimal total = humans.add(mutants);
        BigDecimal ratioMutant = mutants.divide(total, MathContext.DECIMAL64).multiply(PERCENT).setScale(0, RoundingMode.DOWN);

        logger.info("Ratio calculated: {}", ratioMutant);

        return ratioMutant.divide(PERCENT);
    }
}