package br.com.mercadolivre.apiismutant.repository;

import br.com.mercadolivre.apiismutant.domain.Origin;
import br.com.mercadolivre.apiismutant.domain.Stats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatsRepository extends JpaRepository<Stats, Long> {
    long countByOrigin(Origin origin);
}
