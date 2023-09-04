package com.example.springboottfg.services;

import com.example.springboottfg.models.Mecanico;
import com.example.springboottfg.repository.MecanicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MecanicoServiceImpl implements MecanicoService{

    @Autowired
    MecanicoRepository mecanicoRepository;

    @Override
    public List<Mecanico> listAllMecanico(){
        return mecanicoRepository.findAll();
    }

    @Override
    public Mecanico getMecanico(Long id){
        return mecanicoRepository.findById(id).orElse(null);
    }

    @Override
    public Mecanico createMecanico(Mecanico mecanico){
        return mecanicoRepository.save(mecanico);
    }

}
