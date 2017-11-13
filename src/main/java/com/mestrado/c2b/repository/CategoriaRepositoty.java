package com.mestrado.c2b.repository;

import com.mestrado.c2b.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepositoty extends JpaRepository<Categoria, Long> {



}
