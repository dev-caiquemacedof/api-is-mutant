package br.com.mercadolivre.apiismutant.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Getter
@Setter
@Entity
public class Stats {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Origin origin;

    public Stats(Origin origin) {
        this.origin = origin;
    }
}
