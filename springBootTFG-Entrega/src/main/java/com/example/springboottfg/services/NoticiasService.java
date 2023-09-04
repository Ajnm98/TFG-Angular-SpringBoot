package com.example.springboottfg.services;


import com.example.springboottfg.models.Noticias;
import com.example.springboottfg.models.Vehiculo;
import com.example.springboottfg.repository.NoticiasRepository;
import com.example.springboottfg.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticiasService {

    @Autowired
    private NoticiasRepository noticiasRepository;


    public List<Noticias> findAll() {
        return noticiasRepository.obtenerTodo();
    }




}
