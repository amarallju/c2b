package com.mestrado.c2b.entity;

import groovy.transform.builder.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Setter
@Getter
public class TipoUsuario {

    private Long id;
    private String descricao;
}
