package com.mestrado.c2b.entity;

import groovy.transform.builder.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Builder
@Data
@Entity
public class PropostaAnuncio {

    public enum Status {
        ABERTO, EM_ANALISE, RECUSADO, FINALIZADO
    }
    @Id
    @GeneratedValue
    private Long id;
    private Long idAnuncio;
    private BigDecimal valor;
    private Status status;

}
