package com.example.springboottfg.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.engine.profile.Fetch;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity
@Table( name = "reparacion")
public class Reparacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reparacion_estandar_id")
    private EstandarReparacion estandarReparacion;

    @Column()
    @NotNull
    private Date fecha;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;


    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "reparacion_mecanico",
                joinColumns = @JoinColumn(name = "reparacion_id"),
                inverseJoinColumns = @JoinColumn(name = "mecanico_id"))
    private List<Mecanico> mecanicos;




}
