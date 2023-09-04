package com.example.springboottfg.repository;

import com.example.springboottfg.models.Noticias;
import com.example.springboottfg.models.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticiasRepository  extends JpaRepository<Noticias, Long> {

    @Query(value = "select * from noticias",  nativeQuery = true)
    List<Noticias> obtenerTodo();


    @Query(value = " select * from noticias where id = :id ", nativeQuery = true)
    List<Noticias> obtenerNoticiaporId(long id);

}
