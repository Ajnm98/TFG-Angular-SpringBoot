package com.example.springboottfg.services.implementations;

import com.example.springboottfg.models.Usuario;
import com.example.springboottfg.repository.UsuarioRepository;
import com.example.springboottfg.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    public Usuario findById(Long id){
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public Usuario deleteUsuario(String username){
        Usuario usuario = findByUsername(username);
        usuarioRepository.delete(usuario);
        return usuario;
    }



    public Usuario obtenerID(long id){
        return usuarioRepository.obtenerUsuarioid(id);
    }

}
