package com.example.springboottfg.repository;

import com.example.springboottfg.models.Mecanico;
import com.example.springboottfg.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MecanicoRepository extends JpaRepository<Mecanico, Long> {

    @Query(value = "iNSERT INTO mecanico (nombre, apellidos, especialidad,identificador, usuario_id) VALUES (:nombre, :apellidos, :especialidad, :identificacion, :usuario);", nativeQuery = true)
    Mecanico guardar(String nombre, String apellidos, String especialidad, String identificacion, Long usuario);


    @Query(value = "select * from Mecanico where usuario_id = :usuario_id", nativeQuery = true)
    Mecanico findbyUser(Long usuario_id);
}
