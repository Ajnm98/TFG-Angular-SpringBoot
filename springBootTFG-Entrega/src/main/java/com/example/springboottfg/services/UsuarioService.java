package com.example.springboottfg.services;

import com.example.springboottfg.models.Usuario;

public interface UsuarioService {

    Usuario saveUsuario(Usuario usuario);
    Usuario findByUsername(String username);
    Usuario findById(Long id);
    Usuario deleteUsuario(String username);



}
