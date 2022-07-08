package br.com.mercadolivre.apiismutant.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StatsTest {

    @Test
    public void Stats_construct_test() {
        Stats stats = new Stats(Origin.MUTANT);

        Assertions.assertNotNull(stats);
    }

    @Test
    public void Stats_getterSetter_test() {
        Stats stats = new Stats();

        stats.setId(10L);
        stats.setOrigin(Origin.HUMAN);

        Assertions.assertNotNull(stats.getId());
        Assertions.assertNotNull(stats.getOrigin());
    }
}