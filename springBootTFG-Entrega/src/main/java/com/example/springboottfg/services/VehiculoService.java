package com.example.springboottfg.services;


import com.example.springboottfg.models.Usuario;
import com.example.springboottfg.models.Vehiculo;
import com.example.springboottfg.repository.VehiculoRepository;
import org.aspectj.weaver.VersionedDataInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    public List<Vehiculo> buscarVehiculoUsuario(long usuario) {
        return vehiculoRepository.obtenerVehiculoPorUsuario(usuario);
    }

    public void vehiculoEliminar(long id){ vehiculoRepository.deleteById(id);}
    public Vehiculo vehiculoID(long id){
        return vehiculoRepository.findById(id).orElse(null);
    }


}
