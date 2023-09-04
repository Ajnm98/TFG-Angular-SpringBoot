package com.example.springboottfg.controllers;


import com.example.springboottfg.models.ERole;
import com.example.springboottfg.models.Mecanico;
import com.example.springboottfg.models.Role;
import com.example.springboottfg.models.Usuario;
import com.example.springboottfg.repository.MecanicoRepository;
import com.example.springboottfg.repository.RoleRepository;
import com.example.springboottfg.repository.UsuarioRepository;
import com.github.cliftonlabs.json_simple.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/mecanico")
public class MecanicoController {

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    private MecanicoRepository mecanicoRepository;
    @Autowired
    private UsuarioRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/obtener")
    public JsonObject listaMecanico(){
        List<Mecanico> mecanicos = mecanicoRepository.findAll();
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("mecanicoObtener", mecanicos);
        return jsonObject;

    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, value = "/crear")
    public String crearMecanico( @RequestBody Mecanico mecanico){
        Role userRole = roleRepository.findByName(ERole.ROLE_MECANICO).orElse(null);
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);


        Usuario usuario = new Usuario(mecanico.getNombre(), mecanico.getEmail(), encoder.encode(mecanico.getContrasenia()));
        usuario.setRoles(roles);
        userRepository.save(usuario);
        mecanicoRepository.guardar(mecanico.getNombre(), mecanico.getApellidos(), mecanico.getEspecialidad(), mecanico.getIdentificacion(), usuario.getId());

        String status = "creado";
        return status;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.DELETE, value = "/eliminarPorId/{id}")
    public String eliminarPorId( @PathVariable long id){
        mecanicoRepository.deleteById(id);
        String status = "borrado";
        return status;
    }

    @CrossOrigin
    @PostMapping("/editar/{id}")
    public void editarMecanico(@PathVariable long id, @RequestBody Mecanico mecanico){
        Mecanico mecanicoEditar = mecanicoRepository.findById(id).orElse(null);
        mecanicoEditar.setId(id);
        if(mecanico.getNombre()!=""){
            mecanicoEditar.setNombre(mecanico.getNombre());
        }
        if (mecanico.getApellidos() != ""){
            mecanicoEditar.setApellidos(mecanico.getApellidos());
        }
        if(mecanico.getEspecialidad()!=""){
            mecanicoEditar.setEspecialidad(mecanico.getEspecialidad());
        }
        if(mecanico.getIdentificacion()!=""){
            mecanicoEditar.setIdentificacion(mecanico.getIdentificacion());
        }


        mecanicoRepository.save(mecanicoEditar);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/obtenerMecanico/{id}")
    public JsonObject listarMecanicoEditar(@PathVariable long id){

        Mecanico mecanico = mecanicoRepository.findById(id).orElse(null);
        List<Mecanico> mecanicoeditar = new ArrayList<>();
        mecanicoeditar.add(mecanico);
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("mecanicoeditar", mecanicoeditar);
        return jsonObject;
    }


}
