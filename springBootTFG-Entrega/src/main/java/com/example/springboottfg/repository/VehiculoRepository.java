package com.example.springboottfg.repository;

import com.example.springboottfg.models.Usuario;
import com.example.springboottfg.models.Vehiculo;
import com.example.springboottfg.services.VehiculoService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

    @Query(value = "select * from vehiculo",  nativeQuery = true)
    List<Vehiculo> obtenerTodo();

    @Query(value = " select * from vehiculo where usuario_id = :usuario ", nativeQuery = true)
    List<Vehiculo> obtenerVehiculoPorUsuario(long usuario);





}
