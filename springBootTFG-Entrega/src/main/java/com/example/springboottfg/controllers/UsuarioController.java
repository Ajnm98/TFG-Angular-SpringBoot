package com.example.springboottfg.controllers;

import com.example.springboottfg.models.Usuario;
import com.example.springboottfg.repository.User_RoleRepository;
import com.example.springboottfg.repository.UsuarioRepository;
import com.example.springboottfg.services.EmailService;
import com.example.springboottfg.services.UsuarioService2;
import com.github.cliftonlabs.json_simple.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Base64;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private EmailService mailService;



    @Autowired
    private UsuarioService2 usuarioService2;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private User_RoleRepository roleRepository;
    @PostMapping("/crear")
    public Usuario crearUsuario(@RequestBody Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    @GetMapping(value = "/{username}")
    public Usuario getUsuarioByUsername(@PathVariable("username") String username){
        return usuarioRepository.findByUsername(username);
    }

    /**
     * Controlador para recuperar y devolver una lista de todos los usuarios.
     *
     * @return Una lista de objetos Usuario
     */
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/obtenerTodos")
    public JsonObject listarUsuarios(){
        List<Usuario> lista = usuarioRepository.findAll();
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("usuarioObtener", lista);

        // Recuperar todos los usuarios de la base de datos y devolverlos como una lista
        return jsonObject;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/obtenerUsuario1/{id}")
    public JsonObject listarUsuario(@PathVariable Long id
    ){

        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("usuario", usuario);
        return jsonObject;
    }



    @CrossOrigin
    @RequestMapping(method = RequestMethod.DELETE, value = "/eliminarUsuarioId/{id}")
    public String deleteUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        roleRepository.eliminarUsuarioRol(usuario.getId());
        usuarioRepository.deleteById(id);

        String status = "borrado";
        return status;

    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/obtenerUsuario/{id}")
    public JsonObject listarUsuarioEditar(@PathVariable long id){

        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        List<Usuario> usuarioList = new ArrayList<>();
        usuarioList.add(usuario);
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("usuarioListaId", usuarioList);
        return jsonObject;
    }



    @CrossOrigin
    @PostMapping("/editarUsuario/{id}")
    public void editarUsuario(@PathVariable long id, @RequestBody Usuario usuario) {
        Usuario usuarioEditar = usuarioRepository.findById(id).orElse(null);
        usuarioEditar.setId(id);
        if (usuarioEditar.getUsername() != "") {
            usuarioEditar.setUsername(usuario.getUsername());
        }
        if (usuarioEditar.getPassword() != "") {
            usuarioEditar.setPassword(usuario.getPassword());
        }
        if (usuarioEditar.getEmail() != "") {
            usuarioEditar.setEmail(usuario.getEmail());
        }
        usuarioRepository.save(usuarioEditar);
    }


    @CrossOrigin
    @RequestMapping( "/mandarcorreo")
    public String crearvehiculo( @RequestBody Usuario usuario){
        Base64.Encoder encoder1 = Base64.getEncoder();
        String idcodificado = encoder1.encodeToString(usuario.getEmail().getBytes());
        String message = "\nURL para cambiar contraseña: " + "http://localhost:4200/auth/usuariocontraseña/" + idcodificado;

        mailService.sendMail("pfernandezsalas@safareyes.es",usuario.getEmail(),"Cita",message);


        String status = "creado";
        return status;
    }

    @CrossOrigin
    @RequestMapping( "/updateContrasnia")
    public String UpdateContrasnia( @RequestBody Usuario usuario){
        Base64.Decoder decoder = Base64.getDecoder();
        String emaildecodificado = new String(decoder.decode(usuario.getEmail().getBytes()));

        Usuario usuariocontrasenia = usuarioRepository.findByemail(emaildecodificado);
        usuariocontrasenia.setPassword(encoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuariocontrasenia);


        String message = "creado";
        return message;
    }


}