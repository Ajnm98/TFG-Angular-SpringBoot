package com.example.springboottfg.controllers;


import com.example.springboottfg.models.*;
import com.example.springboottfg.models.dto.FacturaDTO;
import com.example.springboottfg.repository.EstandarReparacionRepository;
import com.example.springboottfg.repository.FacturaRepository;
import com.example.springboottfg.repository.ReparacionRepository;
import com.example.springboottfg.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/factura")
public class FacturaController {

    @Autowired
    FacturaRepository facturaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    EstandarReparacionRepository estandarReparacionRepository;

    @Autowired
    ReparacionRepository reparacionRepository;



    @CrossOrigin
    @PostMapping( "/crear")
    public String crearFactura(@RequestBody FacturaDTO facturaDTO){

        Factura factura = new Factura();

        factura.setObservacion(facturaDTO.getObservacion());

        Usuario usuario1 = usuarioRepository.obtenerUsuarioid(facturaDTO.getUsuario());
        factura.setUsuario(usuario1);

        EstandarReparacion reparacion1 = estandarReparacionRepository.obtenerEstandarid(facturaDTO.getReparacion());




        factura.setReparacion(estandarReparacionRepository.obtenerEstandarid(facturaDTO.getReparacion()));
//        factura.getReparacion().setEstandarReparacion(reparacion1);

        factura.setPrecio(facturaDTO.getPrecio());

        factura.setFecha(Timestamp.valueOf(LocalDateTime.now()));

        facturaRepository.save(factura);

        String status = "creado";
        return status;
    }

}
