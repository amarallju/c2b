package com.mestrado.c2b.entity;

import groovy.transform.builder.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@Builder
@Data
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long idCategoria;
    private Integer tipo;
    private String nome;
    private String senha;
    private String email;
    private String cpfCnpj;
}
