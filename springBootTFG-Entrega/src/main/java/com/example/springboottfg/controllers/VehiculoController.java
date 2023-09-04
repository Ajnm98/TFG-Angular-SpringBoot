package com.example.springboottfg.controllers;

import com.example.springboottfg.models.Usuario;
import com.example.springboottfg.models.Vehiculo;
import com.example.springboottfg.repository.UsuarioRepository;
import com.example.springboottfg.repository.VehiculoRepository;
import com.example.springboottfg.services.VehiculoService;
import com.example.springboottfg.services.implementations.UsuarioServiceImpl;
import com.github.cliftonlabs.json_simple.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/vehiculo")
public class VehiculoController {
    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private VehiculoService vehiculoService;

    @Autowired
    private UsuarioServiceImpl usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/obtener")
    public List<Vehiculo> listarVehiculo(){
        return vehiculoRepository.obtenerTodo();

    }
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/obtenerPorUsuario/{id}")
    public JsonObject listarVehiculoPorUsuario(@PathVariable int id
                                                  ){

        List<Vehiculo> vehiculoUsuario = vehiculoService.buscarVehiculoUsuario(id);
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("vehiculoUsuario", vehiculoUsuario);
        return jsonObject;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/obtenerVehiculo/{id}")
    public JsonObject listarVehiculoEditar(@PathVariable long id
    ){

        Vehiculo vehiculoUsuario = vehiculoRepository.findById(id).orElse(null);
        List<Vehiculo> vehiculoList = new ArrayList<>();
        vehiculoList.add(vehiculoUsuario);
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("vehiculoUsuariolista", vehiculoList);
        return jsonObject;
    }


    @CrossOrigin
    @RequestMapping(method = RequestMethod.DELETE, value = "/eliminarPorId/{id}")
    public String eliminarPorId( @PathVariable int id){
        vehiculoService.vehiculoEliminar(id);
        String status = "borrado";
        return status;
    }

    @PostMapping("/editar/{id}")
    public String editarvehiculo(@PathVariable int id, @RequestBody Vehiculo vehiculo,Model model){
        Vehiculo vehiculoeditar = vehiculoService.vehiculoID(id);
        vehiculoeditar.setId(id);
        Usuario usuario = usuarioService.obtenerID(vehiculoeditar.getId_usuario().getId());
        if(vehiculo.getTipoCombustible() != null){
            vehiculoeditar.setTipoCombustible(vehiculo.getTipoCombustible());
        }
        if(vehiculo.getFecha_creacion()!=null){
            vehiculoeditar.setFecha_creacion(vehiculo.getFecha_creacion());
        }
        if(vehiculo.getMatricula()!=""){
            vehiculoeditar.setMatricula(vehiculo.getMatricula());
        }
        if(vehiculo.getModeloVehiculo()!=""){
            vehiculoeditar.setModeloVehiculo(vehiculo.getModeloVehiculo());
        }
        vehiculoeditar.setId_usuario(usuario);
        vehiculoRepository.save(vehiculoeditar);
        String status = "creado";
        return status;
    }

    @CrossOrigin
    @RequestMapping( "/crear/{id}")
    public String crearvehiculo(@PathVariable long id, @RequestBody Vehiculo vehiculo){
        Usuario usuario = usuarioRepository.obtenerUsuarioid(id);
        vehiculo.setId_usuario(usuario);
        vehiculoRepository.save(vehiculo);
        String status = "creado";
        return status;
    }

}

//