package com.mestrado.c2b.controller;

import com.mestrado.c2b.entity.Categoria;
import com.mestrado.c2b.repository.CategoriaRepositoty;
import com.mestrado.c2b.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/categoria")
public class CategoriaApiRestController {

    private CategoriaRepositoty categoriaRepositoty;

    @Autowired
    public CategoriaApiRestController( CategoriaRepositoty categoriaRepositoty) {
        this.categoriaRepositoty = categoriaRepositoty;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listaLivros(Model model) {
        List<Categoria> listaCategorias = categoriaRepositoty.findAll();
        if (listaCategorias != null) {
            model.addAttribute("categorias", listaCategorias);
        }
        return "listaCategorias";
    }

}
