package com.mestrado.c2b.repository;

import com.mestrado.c2b.entity.Anuncio;
import com.mestrado.c2b.entity.PropostaAnuncio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaAnuncioRepositoty extends CrudRepository<PropostaAnuncio, Long> {
    
}
