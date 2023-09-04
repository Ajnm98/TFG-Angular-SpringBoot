package com.example.springboottfg.services;

import com.example.springboottfg.models.Usuario;
import com.example.springboottfg.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService2 {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario copiartodousuario(String usuario){

        List<Usuario> todos = usuarioRepository.findAll();
        Usuario usuario1 = new Usuario();

        for(int i = 0; i < todos.size(); i++ ){
            if(todos.get(i).getUsername().equals(usuario)){
                usuario1 = todos.get(i);
            }
        }

        return usuario1;
    }

    public Usuario cogeridusuario(String user){

        List<Usuario> todos = usuarioRepository.findAll();
        Usuario usuario = new Usuario();

        for(Usuario u : todos){
            if(u.getUsername().equals(user)){
                usuario.setId(u.getId());
                usuario.setRoles(u.getRoles());
                usuario.setUsername(u.getUsername());
                usuario.setPassword(u.getPassword());
                usuario.setEmail(u.getEmail());

            }
        }

        return usuario;
    }

    public void editarCliente(Usuario usuario){

        usuarioRepository.editarUsuario(usuario.getUsername(),usuario.getPassword(),usuario.getId(),usuario.getEmail());

    }


    public void eliminarUsuario(Long id){ usuarioRepository.deleteById(id);}


    public Usuario idUsuario(Long id){
        return usuarioRepository.findById(id).orElse(null);
    }

}