package com.example.springboottfg.controllers;

import com.example.springboottfg.models.DatosUsuario;
import com.example.springboottfg.models.Usuario;
import com.example.springboottfg.services.DatosUsuarioService;
import com.example.springboottfg.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(value = "/datos-usuario", produces = "application/json")
public class DatosUsuarioController {

    @Autowired
    DatosUsuarioService datosUsuarioService;

    @Autowired
    UsuarioService usuarioService;

    @PostMapping(value = "/crear/{id}")
    public DatosUsuario createDatosUsuario(@PathVariable long id,@RequestBody DatosUsuario datosUsuario, Authentication auth) {
        Usuario usuario = usuarioService.findById(id);
        datosUsuario.setUsuario(usuario);
        return datosUsuarioService.createDatosUsuario(datosUsuario);
    }

    @GetMapping(value = "/buscar/{id}")
    public DatosUsuario getDatosUsuarioByNombre(@PathVariable long id) {
        return datosUsuarioService.getDatosUsuarioByIdUser(id);
    }

    @PutMapping(value = "/modificar/{id}")
    public DatosUsuario updateDatosUsuario(@PathVariable Long id, @RequestBody DatosUsuario datosUsuario) {
        datosUsuario.setId(id);
        return datosUsuarioService.updateDatosUsuarioByIdUser(datosUsuario);
    }

}
