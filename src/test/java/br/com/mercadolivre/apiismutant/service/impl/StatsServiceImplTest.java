package br.com.mercadolivre.apiismutant.service.impl;

import br.com.mercadolivre.apiismutant.domain.Origin;
import br.com.mercadolivre.apiismutant.domain.Stats;
import br.com.mercadolivre.apiismutant.dto.StatsDTO;
import br.com.mercadolivre.apiismutant.repository.StatsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StatsServiceImplTest {

    @InjectMocks
    private StatsServiceImpl statsService;

    @Mock
    private StatsRepository repository;

    @Test
    public void StatsServiceImpl_save_test() {
        statsService.save(new Stats());
    }

    @Test
    public void StatsServiceImpl_stats_test() {

        when(repository.countByOrigin(Origin.HUMAN)).thenReturn(1L);
        when(repository.countByOrigin(Origin.MUTANT)).thenReturn(1L);

        StatsDTO stats = statsService.stats();

        Assertions.assertEquals(stats.getCount_human_dna(), 1L);
        Assertions.assertEquals(stats.getCount_mutant_dna(), 1L);
        Assertions.assertEquals(stats.getRatio(), new BigDecimal("0.5"));
    }

    @Test
    public void StatsServiceImpl_calculateRatio_test() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = StatsServiceImpl.class.getDeclaredMethod("calculateRatio", BigDecimal.class, BigDecimal.class);
        method.setAccessible(true);
        BigDecimal calculateRatio = (BigDecimal) method.invoke(statsService, new BigDecimal("1"), new BigDecimal("1"));

        Assertions.assertEquals(calculateRatio, new BigDecimal("0.5"));
    }

    @Test
    public void StatsServiceImpl_calculateRatio_zero_test() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = StatsServiceImpl.class.getDeclaredMethod("calculateRatio", BigDecimal.class, BigDecimal.class);
        method.setAccessible(true);
        BigDecimal calculateRatio = (BigDecimal) method.invoke(statsService, new BigDecimal("1"), new BigDecimal("0"));

        Assertions.assertEquals(calculateRatio, new BigDecimal("0"));
    }
}