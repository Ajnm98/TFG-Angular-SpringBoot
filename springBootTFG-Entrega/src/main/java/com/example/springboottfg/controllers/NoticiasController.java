package com.example.springboottfg.controllers;



import com.example.springboottfg.models.Mecanico;
import com.example.springboottfg.models.Noticias;

import com.example.springboottfg.repository.NoticiasRepository;
import com.example.springboottfg.services.NoticiasService;

import com.github.cliftonlabs.json_simple.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/noticias")
public class NoticiasController {
    @Autowired
    private NoticiasService noticiasService;

    @Autowired
    private NoticiasRepository noticiasRepository;


    @CrossOrigin
    @PostMapping( "/crear")
    public String crearnoticia(@RequestBody Noticias noticias){
       noticiasRepository.save(noticias);
        String status = "creado";
        return status;
    }


    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/obtenernoticias")
    public JsonObject listarTodos(){
        List<Noticias> listnoticias = noticiasRepository.findAll();
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("noticiasObtener", listnoticias);
        return jsonObject;

    }


    @CrossOrigin
    @RequestMapping(method = RequestMethod.DELETE, value = "/eliminarNoticia/{id}")
    public String eliminarPorId( @PathVariable long id){
        noticiasRepository.deleteById(id);
        String status = "borrado";
        return status;
    }


    @CrossOrigin
    @PostMapping("/editar/{id}")
    public void editarNoticia(@PathVariable long id, @RequestBody Noticias noticia) {
        Noticias noticiasEditar = noticiasRepository.findById(id).orElse(null);
        noticiasEditar.setId(id);
        if (noticiasEditar.getTitular() != "") {
            noticiasEditar.setTitular(noticia.getTitular());
        }
        if (noticiasEditar.getCuerpo() != "") {
            noticiasEditar.setCuerpo(noticia.getCuerpo());
        }
        noticiasRepository.save(noticiasEditar);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/obtenerNoticias/{id}")
    public JsonObject listarNoticiasEditar(@PathVariable long id){

        Noticias noticias = noticiasRepository.findById(id).orElse(null);
        List<Noticias> noticiasList = new ArrayList<>();
        noticiasList.add(noticias);
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("noticiasListaId", noticiasList);
        return jsonObject;
    }

}
