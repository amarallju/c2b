package com.mestrado.c2b.repository;

import com.mestrado.c2b.entity.Anuncio;
import com.mestrado.c2b.entity.Categoria;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnuncioRepositoty extends CrudRepository<Anuncio, Long> {


    /**
     * Encontra um Anuncio a partir da descricao do produto.
     * Retorna uma lista pois podem existir
     * mais de um anuncio com mesmo nome.
     *
     * @param descricao
     * @return lista de anuncio
     */
    List<Anuncio> findByDescricao(String descricao);
}
