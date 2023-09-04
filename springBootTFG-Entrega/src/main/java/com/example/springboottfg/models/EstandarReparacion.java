package com.example.springboottfg.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table( name = "reparacion_estandar")
public class EstandarReparacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    @NotNull
    private String nombre;

    @Column()
    private String descripcion;

    @Column()
    private Double precio_estimado;

    @Column()
    private String duracion_estimada;


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "estandarReparacion")
    private List<Cita> cita;





}



