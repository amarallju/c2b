package com.mestrado.c2b.entity;

import groovy.transform.builder.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Builder
@Data
@Entity
public class Usuario {

    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String senha;
    private String email;
    private String cpfCnpj;
}
