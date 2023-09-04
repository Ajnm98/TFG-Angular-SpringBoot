package com.example.springboottfg.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FacturaDTO {

    public String observacion;

    public long usuario;

    public String reparacion;

    public double precio;


}
