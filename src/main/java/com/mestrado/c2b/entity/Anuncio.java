package com.mestrado.c2b.entity;

import groovy.transform.builder.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
@Builder
@Data
@Entity
public class Anuncio implements Serializable{

    public enum Status {
        ABERTO, EM_ANALISE, RECUSADO, FINALIZADO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "Descrição é uma informação obrigatória.")
    private String descricao;

    @Column(nullable = false)
    @NotBlank(message = "Categoria é uma informação obrigatória.")
    private Long idCategoria;

    @Column(nullable = false)
    @NotBlank(message = "Valor é uma informação obrigatória.")
    private BigDecimal valor;

    private Status status;



}
