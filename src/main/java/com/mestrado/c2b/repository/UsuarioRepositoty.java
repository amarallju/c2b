package com.mestrado.c2b.repository;

import com.mestrado.c2b.entity.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositoty extends CrudRepository<Usuario, Long> {

    Usuario findByEmail(String email);

    Usuario findBySenha(String senha);
    
}
