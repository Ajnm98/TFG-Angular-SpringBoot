package com.example.springboottfg.controllers;

import com.example.springboottfg.models.Taller;
import com.example.springboottfg.services.TallerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Method Taller Controller
 */
@RestController
@RequestMapping(value = "/taller")
public class TallerController {

    @Autowired
    TallerService tallerService;

    /**
     *
     * @param id
     * @return taller by ID
     */
    @GetMapping(value = "/{id}")
    public Taller getTallerById(@PathVariable("id") Long id) {
        return tallerService.findById(id);
    }

    /**
     *
     * @return all talleres in the DB
     */
    @GetMapping(value = "/all")
    public List<Taller> getAllTalleres() {
        return tallerService.findAll();
    }

    /**
     *
     * @param taller
     * @return taller created
     */
    @PostMapping(value = "/save")
    public Taller createTaller(@RequestBody Taller taller) {
        return tallerService.save(taller);
    }

    /**
     *
     * @param id
     * @param taller
     * @return taller updated
     */
    @PutMapping(value = "/update/{id}")
    public Taller updateTaller(@PathVariable("id") Long id, @RequestBody Taller taller) {
        taller.setId(id);
        Taller updateTaller = tallerService.update(taller);

        if (taller.getNombre() == null){
            updateTaller.setNombre(updateTaller.getNombre());
        }else {
            updateTaller.setNombre(taller.getNombre());
        }

        if (taller.getDireccion() == null){
            updateTaller.setDireccion(updateTaller.getDireccion());
        }else {
            updateTaller.setDireccion(taller.getDireccion());
        }

        return tallerService.save(updateTaller);
    }

    /**
     * @Method delete Taller
     * @param id
     */
    @DeleteMapping(value = "/delete/{id}")
    public void deleteTaller(@PathVariable("id") Long id) {
        tallerService.delete(id);
    }


}
