package com.example.springboottfg.services;


import com.example.springboottfg.models.Cita;
import com.example.springboottfg.models.Usuario;
import com.example.springboottfg.models.Vehiculo;
import com.example.springboottfg.repository.CitaRepository;
import com.example.springboottfg.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    public List<Cita> buscarVehiculoUsuario(long id) {
        return citaRepository.obtenerVehiculoPorUsuario(id);
    }

    public List<Cita> buscarCitaporFecha(String fecha_inicio, String fecha_fin) {
        return citaRepository.obtenerCita(fecha_inicio, fecha_fin);
    }

    public List<Cita> findByMecanico(long id) {
        return citaRepository.findMecanico(id);
    }



}
