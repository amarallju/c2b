package com.mestrado.c2b.repository;

import com.mestrado.c2b.entity.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepositoty extends CrudRepository<Usuario, Long> {

    Usuario findById(Long id);

    Usuario findByEmail(String email);

    List<Usuario> findBySenha(String senha);

    List<Usuario> findByTipo(Integer tipo);
    
}
