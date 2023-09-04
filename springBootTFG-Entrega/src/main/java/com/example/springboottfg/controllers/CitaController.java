package com.example.springboottfg.controllers;

import com.example.springboottfg.models.*;
import com.example.springboottfg.repository.*;
import com.example.springboottfg.services.CitaService;
import com.example.springboottfg.services.EmailService;
import com.example.springboottfg.services.implementations.UsuarioServiceImpl;
import com.github.cliftonlabs.json_simple.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cita")
public class CitaController {
    @Autowired
    private MecanicoRepository mecanicoRepository;

    @Autowired
    private EmailService mailService;

    @Autowired
    private CitaService citaService;

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private UsuarioServiceImpl usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private EstandarReparacionRepository estandarReparacionRepository;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/obtenerPorUsuario/{id}")
    public JsonObject listaCitaPorUsuario(@PathVariable("id") long id,
                                               Model model){
        List<Cita> citasObtenerUsuario = citaService.buscarVehiculoUsuario(id);
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("citasObtenerUsuario", citasObtenerUsuario);
        return jsonObject;
    }


    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/obtener/{id}")
    public List<Cita> listaTodos(@PathVariable("id") long id){
        List<Cita> citaUsuario = citaService.findByMecanico(id);
        return citaUsuario;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.DELETE, value ="/eliminarPorId/{id}")
    public void eliminarPorId( @PathVariable long id){
        citaRepository.deleteById(id);
    }

    @CrossOrigin
    @RequestMapping("/crear/{id}/{mecanico}/{estandar}")
    public String crearCita(@PathVariable long id,@PathVariable long mecanico,@PathVariable long estandar, @RequestBody Cita cita){
        Usuario usuario = usuarioRepository.obtenerUsuarioid(id);
        Mecanico mecanicoid = mecanicoRepository.findbyUser(mecanico);
        EstandarReparacion estandarReparacion = estandarReparacionRepository.findById(estandar).orElse(null);
//        Vehiculo vehiculo1 = vehiculoRepository.findById(vehiculo).orElse(null);

        String message = "\nNombre: " + usuario.getUsername() + "\nE-mail: " + usuario.getEmail()
                + "\nHora de la cita: " + cita.getStart().toLocalTime() + "\nFecha de la cita: " + cita.getStart().toLocalDate() +
                "\nDescripción de la cita: " + cita.getTitle() + "\nMecánico: " + mecanicoid.getNombre() + "\nReparación: " + estandarReparacion.getNombre()
                + "\nDuración de la reparación: " + estandarReparacion.getDuracion_estimada() + "\nPrecio de la reparación: " + estandarReparacion.getPrecio_estimado();

        mailService.sendMail("pfernandezsalas@safareyes.es",usuario.getEmail(),"Cita",message);

        cita.setId_mecanico(mecanicoid);
        cita.setDisplay(id);
        cita.setId_usuario(usuario);
        cita.setEstandarReparacion(estandarReparacion);
        cita.setTitle(estandarReparacion.getDescripcion());


//        cita.setId_vehiculo(vehiculo1);
        citaRepository.save(cita);
        String status = "Creado";
        return status;

    }






    @PostMapping("/editar/{id}")
    public void editarCita(@PathVariable long id, @RequestBody Cita cita){
        Cita citaeditar = citaRepository.findById(id).orElse(null);
        citaeditar.setTitle(cita.getTitle());
        citaeditar.setStart(cita.getStart());
        citaeditar.setEnd(cita.getEnd());
        Usuario usuario = usuarioService.obtenerID(citaeditar.getId_usuario().getId());
        Vehiculo vehiculo = vehiculoRepository.findById(citaeditar.getId_usuario().getId()).orElse(null);
        citaeditar.setId_usuario(usuario);
        citaeditar.setId_vehiculo(vehiculo);
        citaRepository.save(citaeditar);
    }

    @CrossOrigin
    @PostMapping("/editartienda")
    public void editarTienda(@RequestBody Cita cita){
        Cita citaeditar = citaRepository.findById(cita.getId()).orElse(null);
        citaeditar.setPagar_tienda(1);
        citaRepository.save(citaeditar);
    }

    @CrossOrigin
    @PostMapping("/editarpagado")
    public void editarPagado(@RequestBody Cita cita){
        Cita citaeditar = citaRepository.findById(cita.getId()).orElse(null);
        citaeditar.setPagar_ahora(1);
        citaRepository.save(citaeditar);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/obtenerporfecha/{fecha_inicio}/{fecha_fin}")
    public List<Cita> listarporfecha(@PathVariable("fecha_inicio") String fecha_inicio,@PathVariable("fecha_fin") String fecha_fin, Model model){
        List<Cita> citaUsuario = citaService.buscarCitaporFecha(fecha_inicio, fecha_fin);
        model.addAttribute("cita", citaUsuario);
        return citaUsuario;

    }
}
