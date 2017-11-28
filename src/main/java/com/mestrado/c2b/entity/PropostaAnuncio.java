package com.mestrado.c2b.entity;

import groovy.transform.builder.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@Builder
@Data
@Entity
public class PropostaAnuncio {

    public enum Status {
        ABERTO, ACEITO, RECUSADO
    }
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long idAnuncio;
    private BigDecimal valor;
    private Date data;
    private Status status;

}
