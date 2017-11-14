package com.mestrado.c2b.controller;

import com.mestrado.c2b.entity.Categoria;
import com.mestrado.c2b.repository.CategoriaRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/categoria")
public class CategoriaApiRestController {

    private CategoriaRepositoty categoriaRepositoty;

    @Autowired
    public CategoriaApiRestController( CategoriaRepositoty categoriaRepositoty) {
        this.categoriaRepositoty = categoriaRepositoty;
    }

    @RequestMapping(value = "/listaCategorias", method = RequestMethod.GET)
    public String listaCategorias(Model model) {
        List<Categoria> listaCategorias = categoriaRepositoty.findAll();
        if (listaCategorias != null) {
            model.addAttribute("categorias", listaCategorias);
        }
        return "listaCategorias";
    }

}
