package com.example.springboottfg.services;

import com.example.springboottfg.models.Mecanico;

import java.util.List;

public interface MecanicoService {

    List<Mecanico> listAllMecanico();
    Mecanico getMecanico(Long id);
    Mecanico createMecanico(Mecanico mecanico);

}
