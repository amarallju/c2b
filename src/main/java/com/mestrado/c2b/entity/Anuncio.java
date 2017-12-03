package com.mestrado.c2b.entity;

import groovy.transform.builder.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@Builder
@Data
@Entity
public class Anuncio implements Serializable{

    public enum Status {
        ABERTO, ACEITO, RECUSADO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 50)
    private String descricao;

    @Column(nullable = false)
    private Long idCategoria;

    @Column(nullable = false)
    private BigDecimal valor;

    private Status status;

    private String descricaoCategria;

    private BigDecimal valorProposta;

    @Column(nullable = false)
    private Long idUsuario;

    private String imagem;
}
