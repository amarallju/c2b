package com.mestrado.c2b.controller;

import com.mestrado.c2b.entity.Anuncio;
import com.mestrado.c2b.entity.Categoria;
import com.mestrado.c2b.entity.PropostaAnuncio;
import com.mestrado.c2b.entity.Usuario;
import com.mestrado.c2b.repository.AnuncioRepositoty;
import com.mestrado.c2b.repository.CategoriaRepositoty;
import com.mestrado.c2b.repository.PropostaAnuncioRepositoty;
import com.mestrado.c2b.repository.UsuarioRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class AnuncioApiRestController {

    private AnuncioRepositoty anuncioRepositoty;
    private CategoriaRepositoty categoriaRepositoty;
    private PropostaAnuncioRepositoty propostaAnuncioRepositoty;
    private UsuarioRepositoty usuarioRepositoty;

    private Long ID_ANUNCIO = new Long("0");

    public String NOME_USUARIO = "";
    public Long ID_USUARIO = new Long("0");

    @Autowired
    public AnuncioApiRestController( AnuncioRepositoty anuncioRepositoty, CategoriaRepositoty categoriaRepositoty,
            PropostaAnuncioRepositoty propostaAnuncioRepositoty, UsuarioRepositoty usuarioRepositoty) {
        this.anuncioRepositoty = anuncioRepositoty;
        this.categoriaRepositoty = categoriaRepositoty;
        this.propostaAnuncioRepositoty = propostaAnuncioRepositoty;
        this.usuarioRepositoty = usuarioRepositoty;
    }

    @RequestMapping(value = "/proposta/{id}", method = RequestMethod.GET)
    public String propostaAnuncio(HttpServletRequest request, @PathVariable String id, Model model) {

        Anuncio anuncio = anuncioRepositoty.findById(new Long(id));
        List<PropostaAnuncio> propostaList = propostaAnuncioRepositoty.findByIdAnuncio(new Long(id));
        ID_ANUNCIO = new Long(id);

        anuncio.setDescricaoCategria(pesquisaDescricaoCategoria(anuncio.getIdCategoria()));

        model.addAttribute("anuncio", anuncio);

        return "proposta";
    }

    @RequestMapping(value = "/detalheAnuncio/{id}", method = RequestMethod.GET)
    public String detalheAnuncio(HttpServletRequest request, @PathVariable String id, Model model) {

        Anuncio anuncio = anuncioRepositoty.findById(new Long(id));
        List<PropostaAnuncio> propostaList = propostaAnuncioRepositoty.findByIdAnuncio(new Long(id));

        anuncio.setDescricaoCategria(pesquisaDescricaoCategoria(anuncio.getIdCategoria()));

        model.addAttribute("propostaList", propostaList);
        model.addAttribute("anuncio", anuncio);

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

        for (Anuncio anuncio: listaAnuncios){
            anuncio.setDescricaoCategria(pesquisaDescricaoCategoria(anuncio.getIdCategoria()));
        }

        model.addAttribute("usuarioLogado", NOME_USUARIO);

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

        for (Anuncio anunciolista: listaAnuncios){
            anunciolista.setDescricaoCategria(pesquisaDescricaoCategoria(anunciolista.getIdCategoria()));
        }

        model.addAttribute("usuarioLogado", NOME_USUARIO);

        if (listaAnuncios != null) {
            model.addAttribute("anuncios", listaAnuncios);
            model.addAttribute("anuncio", new Anuncio());
        }
        return "index";
    }

    @PostMapping(value = "/save")
    public String adicionarAnuncio(@Valid Anuncio anuncio, BindingResult result, Model model) {

        anuncio.setStatus(Anuncio.Status.ABERTO);
        anuncio.setIdUsuario(ID_USUARIO);
        anuncioRepositoty.save(anuncio);

        List<Anuncio> listaAnuncios = (List<Anuncio>) anuncioRepositoty.findAll();

        for (Anuncio anunciolista: listaAnuncios){
            anunciolista.setDescricaoCategria(pesquisaDescricaoCategoria(anunciolista.getIdCategoria()));
        }

        model.addAttribute("usuarioLogado", NOME_USUARIO);

        if (listaAnuncios != null) {
            model.addAttribute("anuncios", listaAnuncios);
            model.addAttribute("anuncio", new Anuncio());
        }
        return "index";

    }

    @PostMapping(value = "/saveProposta")
    public String adicionarPropostaAnuncio(@Valid Anuncio anuncio, BindingResult result, Model model) {

        PropostaAnuncio proposta = new PropostaAnuncio();
        proposta.setData(new Date());
        proposta.setIdAnuncio(ID_ANUNCIO);
        proposta.setValor(anuncio.getValorProposta());
        proposta.setStatus(PropostaAnuncio.Status.ABERTO);

        propostaAnuncioRepositoty.save(proposta);

        List<Anuncio> listaAnuncios = (List<Anuncio>) anuncioRepositoty.findAll();

        for (Anuncio anunciolista: listaAnuncios){
            anunciolista.setDescricaoCategria(pesquisaDescricaoCategoria(anunciolista.getIdCategoria()));
        }

        model.addAttribute("usuarioLogado", NOME_USUARIO);

        if (listaAnuncios != null) {
            model.addAttribute("anuncios", listaAnuncios);
            model.addAttribute("anuncio", new Anuncio());
        }
        return "index";

    }

    @RequestMapping(value = "/recusarProposta/{id}", method = RequestMethod.GET)
    public String recusarProposta(HttpServletRequest request, @PathVariable String id, Model model) {

        PropostaAnuncio proposta = propostaAnuncioRepositoty.findById(new Long(id));
        proposta.setStatus(PropostaAnuncio.Status.RECUSADO);

        propostaAnuncioRepositoty.save(proposta);

        Anuncio anuncio = anuncioRepositoty.findById(ID_ANUNCIO);
        List<PropostaAnuncio> propostaList = propostaAnuncioRepositoty.findByIdAnuncio(ID_ANUNCIO);

        anuncio.setDescricaoCategria(pesquisaDescricaoCategoria(anuncio.getIdCategoria()));

        model.addAttribute("propostaList", propostaList);
        model.addAttribute("anuncio", anuncio);

        return "detalheAnuncio";
    }

    @PostMapping(value = "/makeLogin")
    public String makeLogin(@Valid Usuario usuario, BindingResult result, Model model) {

        Usuario usuarioSave = new Usuario();

        Usuario usuarioEmail = usuarioRepositoty.findByEmail(usuario.getEmail());
        if (usuarioEmail == null){
            return "login";
        }

        usuarioSave = usuarioRepositoty.findBySenha(usuario.getSenha());
        if (usuarioSave == null){

            return "login";
        }

        NOME_USUARIO = usuarioSave.getNome();
        ID_USUARIO = usuarioSave.getId();

        List<Anuncio> listaAnuncios = (List<Anuncio>) anuncioRepositoty.findAll();

        for (Anuncio anunciolista: listaAnuncios){
            anunciolista.setDescricaoCategria(pesquisaDescricaoCategoria(anunciolista.getIdCategoria()));
        }

        model.addAttribute("usuarioLogado", NOME_USUARIO);

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


    private String pesquisaDescricaoCategoria(Long idCategoria){

        Categoria categoria = categoriaRepositoty.findById(idCategoria);
        return categoria.getDescricao();

    }
}
