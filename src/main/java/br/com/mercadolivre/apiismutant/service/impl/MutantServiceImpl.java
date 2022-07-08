package br.com.mercadolivre.apiismutant.service.impl;

import br.com.mercadolivre.apiismutant.domain.Origin;
import br.com.mercadolivre.apiismutant.domain.Stats;
import br.com.mercadolivre.apiismutant.exception.MutantException;
import br.com.mercadolivre.apiismutant.service.MutantService;
import br.com.mercadolivre.apiismutant.service.StatsService;
import br.com.mercadolivre.apiismutant.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class MutantServiceImpl implements MutantService {
    private static final Logger logger = LoggerFactory.getLogger(MutantServiceImpl.class);
    private final StatsService statsService;

    @Autowired
    public MutantServiceImpl(StatsService statsService) {
        this.statsService = statsService;
    }

    @Override
    public boolean isMutant(String[] dna) throws MutantException {
        try {
            logger.info("Stating analyze mutant...'");

            List<String> dnaGenes = Arrays.asList(dna);
            validateInputGenes(dnaGenes);

            long totalOccurrences = getOccurrences(dnaGenes);
            totalOccurrences += getOccurrences(collectItemsInVertical(dnaGenes));
            totalOccurrences += getOccurrences(collectItemsInDiagonal(dnaGenes));

            logger.info("Occurrences founded, {}", totalOccurrences);

            return isMutant(totalOccurrences);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new MutantException(e.getMessage());
        }
    }

    private boolean isMutant(Long occurrences) {
        if (occurrences > Util.baseNumberSequenceGene) {
            logger.info("Mutant DNA found!");
            statsService.save(new Stats(Origin.MUTANT));
            return Boolean.TRUE;
        }

        logger.info("Mutant not found!");
        statsService.save(new Stats(Origin.HUMAN));

        return Boolean.FALSE;
    }

    private List<String> collectItemsInDiagonal(List<String> dnaGenes) {
        logger.info("collect items in diagonal...");
        return Arrays.asList(IntStream
                .range(0, dnaGenes.size())
                .mapToObj(i -> dnaGenes.get(i).split(Util.EMPTY_STRING)[i])
                .collect(Collectors.joining()));
    }

    private List<String> collectItemsInVertical(List<String> dnaGenes) {
        logger.info("collect items in vertical...");

        Map<Long, String> horizontalMap = new HashMap<>();

        for (String dnaGene : dnaGenes) {

            AtomicLong count = new AtomicLong();

            Stream.of(dnaGene.split(Util.EMPTY_STRING)).forEach(newValue -> {

                if (horizontalMap.containsKey(count.get())) {
                    String oldValue = horizontalMap.get(count.get());
                    horizontalMap.put(count.get(), String.join(Util.EMPTY_STRING, oldValue, newValue));
                } else {
                    horizontalMap.put(count.get(), newValue);
                }

                count.incrementAndGet();

            });
        }

        return new ArrayList(horizontalMap.values());
    }

    private long getOccurrences(List<String> dna) {
        logger.info("Verifying occurrences...");
        return dna.stream().filter(gene -> Util.dnaMatchPattern.matcher(gene).find()).count();
    }

    private void validateInputGenes(List<String> dna) {
        logger.info("Validating input...");
        if (dna.isEmpty()) {
            throw new IllegalArgumentException("Human are empty!");
        }

        containInValidGenes(dna);
    }

    private void containInValidGenes(List<String> dna) {
        dna.stream().filter(gene -> Util.noDnaMatch.matcher(gene).find()).findFirst().ifPresent(s -> {
            throw new IllegalArgumentException("Invalid fields founded:" + s);
        });
    }
}
