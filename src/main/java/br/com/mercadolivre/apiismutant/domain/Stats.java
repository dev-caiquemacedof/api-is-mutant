package br.com.mercadolivre.apiismutant.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Getter
@Setter
@Entity
@NoArgsConstructor
public class Stats {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Origin origin;

    public Stats(Origin origin) {
        this.origin = origin;
    }
}
