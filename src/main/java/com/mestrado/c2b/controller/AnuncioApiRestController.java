package com.mestrado.c2b.controller;

import com.mestrado.c2b.entity.Anuncio;
import com.mestrado.c2b.entity.Categoria;
import com.mestrado.c2b.repository.AnuncioRepositoty;
import com.mestrado.c2b.repository.CategoriaRepositoty;
import com.mestrado.c2b.repository.PropostaAnuncioRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class AnuncioApiRestController {

    private AnuncioRepositoty anuncioRepositoty;
    private CategoriaRepositoty categoriaRepositoty;
    private PropostaAnuncioRepositoty propostaAnuncioRepositoty;

    @Autowired
    public AnuncioApiRestController( AnuncioRepositoty anuncioRepositoty, CategoriaRepositoty categoriaRepositoty,
            PropostaAnuncioRepositoty propostaAnuncioRepositoty) {
        this.anuncioRepositoty = anuncioRepositoty;
        this.categoriaRepositoty = categoriaRepositoty;
        this.propostaAnuncioRepositoty = propostaAnuncioRepositoty;
    }

    @GetMapping(value = "/detalheAnuncio{id}")
    public String detalheAnuncio(@RequestParam(value="id", defaultValue="10") Long id, Model model) {

        model.addAttribute("propostaList", propostaAnuncioRepositoty.findByIdAnuncio(id));
        model.addAttribute("anuncio", anuncioRepositoty.findById(id));

        return "detalheAnuncio";
    }

    @GetMapping("/add")
    public String add(Model model) {

        model.addAttribute("categoriaList", listaCategoriaAll() );
        model.addAttribute("anuncio", new Anuncio());
        return "anuncio";
    }

    @RequestMapping(value = "/listaAnuncios", method = RequestMethod.GET)
    public String listaAnuncios(Model model) {
        List<Anuncio> listaAnuncios = (List<Anuncio>) anuncioRepositoty.findAll();
        if (listaAnuncios != null) {
            model.addAttribute("anuncios", listaAnuncios);
            model.addAttribute("anuncio", new Anuncio());
        }
        return "index";
    }

    @PostMapping(value = "/listaBydescricao")
    public String listaAnuncioByDescricao(@Valid Anuncio anuncio, BindingResult result, Model model) {
        List<Anuncio> listaAnuncios = new ArrayList<Anuncio>();
        if (anuncio.getDescricao() == null || anuncio.getDescricao().equals("")){
            listaAnuncios = (List<Anuncio>) anuncioRepositoty.findAll();
        }else{
            listaAnuncios = anuncioRepositoty.findByDescricao(anuncio.getDescricao());

        }
        if (listaAnuncios != null) {
            model.addAttribute("anuncios", listaAnuncios);
            model.addAttribute("anuncio", new Anuncio());
        }
        return "index";
    }

    @PostMapping(value = "/save")
    public String adicionarAnuncio(@Valid Anuncio anuncio, BindingResult result, Model model) {

        anuncio.setStatus(Anuncio.Status.ABERTO);
        anuncioRepositoty.save(anuncio);

        List<Anuncio> listaAnuncios = (List<Anuncio>) anuncioRepositoty.findAll();
        if (listaAnuncios != null) {
            model.addAttribute("anuncios", listaAnuncios);
            model.addAttribute("anuncio", new Anuncio());
        }
        return "index";

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
