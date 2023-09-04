package com.example.springboottfg.repository;

import com.example.springboottfg.models.EstandarReparacion;
import com.example.springboottfg.models.Reparacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReparacionRepository  extends JpaRepository<Reparacion,Long> {
}
