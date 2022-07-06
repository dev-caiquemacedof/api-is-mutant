package br.com.mercadolivre.apiismutant.service;

import br.com.mercadolivre.apiismutant.exception.MutantException;

public interface MutantService {
    boolean isMutant(String[] dna) throws MutantException;
}