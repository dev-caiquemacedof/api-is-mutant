package br.com.mercadolivre.apiismutant.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class HumanTest {

    @Test
    public void Human_construct_test() {
        Human human = new Human(new String[]{"CTGAGA", "CTGAGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"});

        Assertions.assertNotNull(human);
    }

    @Test
    public void Human_getterSetter_test() {
        Human human = new Human();

        human.setDna(new String[]{"CTGAGA", "CTGAGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"});

        Assertions.assertNotNull(human.getDna());
    }
}