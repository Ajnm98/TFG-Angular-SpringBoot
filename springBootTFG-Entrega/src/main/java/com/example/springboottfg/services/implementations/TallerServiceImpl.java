package com.example.springboottfg.services.implementations;

import com.example.springboottfg.models.Taller;
import com.example.springboottfg.repository.TallerRepository;
import com.example.springboottfg.services.TallerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TallerServiceImpl implements TallerService {

    @Autowired
    TallerRepository tallerRepository;

    @Override
    public List<Taller> findAll() {
        return tallerRepository.findAll();
    }

    @Override
    public Taller findById(Long id) {
        return tallerRepository.findById(id).orElse(null);
    }

    @Override
    public Taller save(Taller taller) {
        return tallerRepository.save(taller);
    }

    @Override
    public Taller update(Taller taller) {
        Taller getTallerById = findById(taller.getId());
        if (getTallerById == null){
            return null;
        }
        return tallerRepository.save(getTallerById);
    }

    @Override
    public void delete(Long id) {
        tallerRepository.delete(findById(id));
    }



}
