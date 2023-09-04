package com.example.springboottfg.repository;

import com.example.springboottfg.models.EstandarReparacion;
import com.example.springboottfg.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EstandarReparacionRepository extends JpaRepository<EstandarReparacion,Long> {

    @Query(value = "select * from reparacion_estandar where nombre = :reparacion", nativeQuery = true)
    EstandarReparacion obtenerEstandarid(String reparacion);


}
