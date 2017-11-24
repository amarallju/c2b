package com.mestrado.c2b.controller;

import com.mestrado.c2b.entity.Anuncio;
import com.mestrado.c2b.entity.Categoria;
import com.mestrado.c2b.entity.TipoUsuario;
import com.mestrado.c2b.entity.Usuario;
import com.mestrado.c2b.repository.AnuncioRepositoty;
import com.mestrado.c2b.repository.CategoriaRepositoty;
import com.mestrado.c2b.repository.UsuarioRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UsuarioController {

    private CategoriaRepositoty categoriaRepositoty;
    private UsuarioRepositoty usuarioRepositoty;
    private AnuncioRepositoty anuncioRepositoty;

    public String NOME_USUARIO = "";
    public Long ID_USUARIO = new Long("0");

    @Autowired
    public UsuarioController(AnuncioRepositoty anuncioRepositoty, CategoriaRepositoty categoriaRepositoty,
                             UsuarioRepositoty usuarioRepositoty) {
        this.categoriaRepositoty = categoriaRepositoty;
        this.usuarioRepositoty = usuarioRepositoty;
        this.anuncioRepositoty = anuncioRepositoty;
    }

    @GetMapping("/loginUsuario")
    public String loginUsuario(Model model) {

        model.addAttribute("usuario", new Usuario());
        return "login";
    }

    @GetMapping("/addUsuario")
    public String addUsuario(Model model) {

        model.addAttribute("categoriaList", listaCategoriaAll() );
        model.addAttribute("tipoLista", listaTipoUsuarioAll() );
        model.addAttribute("usuario", new Usuario());
        return "usuario";
    }

    @PostMapping(value = "/saveUsuario")
    public String adicionarUsuario(@Valid Usuario usuario, BindingResult result, Model model) {

        Usuario usuarioSave = usuarioRepositoty.save(usuario);

        NOME_USUARIO = usuarioSave.getNome();
        ID_USUARIO = usuarioSave.getId();

        List<Anuncio> listaAnuncios = (List<Anuncio>) anuncioRepositoty.findAll();

        for (Anuncio anunciolista: listaAnuncios){
            anunciolista.setDescricaoCategria(pesquisaDescricaoCategoria(anunciolista.getIdCategoria()));
        }

        if (listaAnuncios != null) {
            model.addAttribute("anuncios", listaAnuncios);
            model.addAttribute("anuncio", new Anuncio());
        }
        return "index";

    }

    private String pesquisaDescricaoCategoria(Long idCategoria){

        Categoria categoria = categoriaRepositoty.findById(idCategoria);
        return categoria.getDescricao();

    }

    private List<TipoUsuario> listaTipoUsuarioAll(){

        List<TipoUsuario> listaTipoUsuario = new ArrayList<TipoUsuario>();

        TipoUsuario tipo1 = new TipoUsuario();
        tipo1.setId(new Long(1));
        tipo1.setDescricao("Vendedor");
        listaTipoUsuario.add(tipo1);

        TipoUsuario tipo2 = new TipoUsuario();
        tipo2.setId(new Long(2));
        tipo2.setDescricao("Comprador");
        listaTipoUsuario.add(tipo2);

        return listaTipoUsuario;
    }

    private List<Categoria> listaCategoriaAll(){

        List<Categoria> listaCategorias = new ArrayList<Categoria>();
        Categoria cat1 = new Categoria();
        cat1.setId(new Long(1));
        cat1.setDescricao("Eletronico");
        categoriaRepositoty.save(cat1);

        Categoria cat2 = new Categoria();
        cat2.setId(new Long(2));
        cat2.setDescricao("Movéis");
        categoriaRepositoty.save(cat2);

        Categoria cat3 = new Categoria();
        cat3.setId(new Long(3));
        cat3.setDescricao("Telefonia");
        categoriaRepositoty.save(cat3);

        Categoria cat4 = new Categoria();
        cat4.setId(new Long(4));
        cat4.setDescricao("Diversos");
        categoriaRepositoty.save(cat4);

        Categoria cat5 = new Categoria();
        cat5.setId(new Long(5));
        cat5.setDescricao("Brinquedos");
        categoriaRepositoty.save(cat5);

        listaCategorias.addAll(categoriaRepositoty.findAll());

        return listaCategorias;
    }
}
