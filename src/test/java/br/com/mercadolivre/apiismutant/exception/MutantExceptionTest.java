package br.com.mercadolivre.apiismutant.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MutantExceptionTest {

    @Test
    public void MutantException_construct_test() {
        MutantException mutantFound = new MutantException("mutant found");

        Assertions.assertNotNull(mutantFound);
    }
}