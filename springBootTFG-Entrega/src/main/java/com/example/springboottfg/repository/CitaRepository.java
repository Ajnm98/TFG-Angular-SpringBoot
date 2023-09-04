package com.example.springboottfg.repository;

import com.example.springboottfg.models.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CitaRepository extends JpaRepository<Cita,Long> {

    @Query(value = " select * from cita where usuario_id = :id ", nativeQuery = true)
    List<Cita>obtenerVehiculoPorUsuario(long id);

    @Query(value = " select * from cita c, mecanico m WHERE c.mecanico_id = m.id and m.usuario_id = :id", nativeQuery = true)
    List<Cita>findMecanico(long id);


    @Query(value = " select * from cita where reservado = 0 and fecha_inicio between :fecha_inicio and :fecha_fin and fecha_fin between :fecha_inicio and :fecha_fin", nativeQuery = true)
    List<Cita>obtenerCita(String fecha_inicio, String fecha_fin);

    @Query(value = " select * from cita where id = :id ", nativeQuery = true)
    Cita findCita(long id);

}
