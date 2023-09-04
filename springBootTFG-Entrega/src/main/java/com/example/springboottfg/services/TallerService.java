package com.example.springboottfg.services;

import com.example.springboottfg.models.Taller;

import java.util.List;

public interface TallerService {

    List<Taller> findAll();
    Taller findById(Long id);
    Taller save(Taller taller);
    void delete(Long id);
    Taller update(Taller taller);

}
