package com.mestrado.c2b.controller;

import com.mestrado.c2b.entity.Anuncio;
import com.mestrado.c2b.repository.AnuncioRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/")
public class AnuncioApiRestController {

    private AnuncioRepositoty anuncioRepositoty;

    @Autowired
    public AnuncioApiRestController( AnuncioRepositoty anuncioRepositoty) {
        this.anuncioRepositoty = anuncioRepositoty;
    }

    @GetMapping("/add")
    public String add() {

        return "anuncio";
    }

    @RequestMapping(value = "/listaAnuncios", method = RequestMethod.GET)
    public String listaAnuncios(Model model) {
        List<Anuncio> listaAnuncios = (List<Anuncio>) anuncioRepositoty.findAll();
        if (listaAnuncios != null) {
            model.addAttribute("anuncios", listaAnuncios);
        }
        return "index";
    }

    @PostMapping(value = "/listaBydescricao")
    public String listaAnuncioByDescricao(@Valid Anuncio anuncio, BindingResult result, Model model) {
        List<Anuncio> listaAnuncios = anuncioRepositoty.findByDescricao(anuncio.getDescricao());
        if (listaAnuncios != null) {
            model.addAttribute("anuncios", listaAnuncios);
        }
        return "index";
    }

    @PostMapping(value = "/save")
    public String adicionarAnuncio(@Valid Anuncio anuncio, BindingResult result) {
        anuncioRepositoty.save(anuncio);
        return "redirect:/{listaAnuncios}";
    }
}
