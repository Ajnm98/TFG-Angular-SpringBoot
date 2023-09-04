package com.example.springboottfg.services;

import com.example.springboottfg.exceptions.NotFoundException;
import com.example.springboottfg.models.EstandarReparacion;
import com.example.springboottfg.repository.EstandarReparacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstandarReparacionService {

    @Autowired
    private EstandarReparacionRepository estandarReparacionRepository;


    public List<EstandarReparacion> findAllEstandarReparacion(){
        List<EstandarReparacion>  lista = new ArrayList<>();
        try {
            lista = estandarReparacionRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return lista;
    }


    public EstandarReparacion crearEstandarReparacion(EstandarReparacion estandarReparacion) throws NotFoundException{
        try {
            return estandarReparacionRepository.save(estandarReparacion);
        }catch (Exception e){
            throw new NotFoundException(HttpStatus.NOT_FOUND.value(),e.getMessage());
        }
    }



    public void borrarEstandarReparacion(Long id) throws NotFoundException{
        EstandarReparacion er = estandarReparacionRepository.findById(id).orElse(null);
        if (er != null){
            try{
                estandarReparacionRepository.delete(er);
            }catch (Exception e){
                throw new NotFoundException(HttpStatus.NOT_FOUND.value(), e.getMessage());
            }
        }
    }



    public EstandarReparacion updateEstandarReparacion(EstandarReparacion estandarReparacion){
        EstandarReparacion er = estandarReparacionRepository.findById(estandarReparacion.getId()).orElse(null);
        if (er == null){
            return null;
        }

        er.setNombre(estandarReparacion.getNombre());
        er.setPrecio_estimado(estandarReparacion.getPrecio_estimado());
        er.setDuracion_estimada(estandarReparacion.getDuracion_estimada());
        er.setDescripcion(estandarReparacion.getDescripcion());
//      er.setReparaciones(estandarReparacion.getReparaciones());

        try{
            estandarReparacionRepository.save(er);
        }catch (Exception e){
            e.printStackTrace();
        }

        return er;
    }


}
