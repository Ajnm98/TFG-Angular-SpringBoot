package com.example.springboottfg.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "Cita")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "fecha_inicio")
    private LocalDateTime start;

    @Column(name = "fecha_fin")
    private LocalDateTime end;

    @Column(name = "descripcion")
    private String title;

    @Column(name = "pagar_ahora")
    private long pagar_ahora;

    @Column(name = "pagar_tienda")
    private long pagar_tienda;

    @Column(name = "usuario_numero")
    private long display;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario id_usuario;

    @JsonIgnoreProperties(ignoreUnknown = true,
            value = {"hibernateLazyInitializer", "handler", "created"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mecanico_id")
    private Mecanico id_mecanico;

    @JsonIgnoreProperties(ignoreUnknown = true,
            value = {"hibernateLazyInitializer", "handler", "created"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo id_vehiculo;
//    @JsonIgnoreProperties(ignoreUnknown = true,
//            value = {"hibernateLazyInitializer", "handler", "created"})
    @JsonIgnoreProperties(ignoreUnknown = true,
        value = {"hibernateLazyInitializer", "handler", "created"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reparacion_estandar_id")
    private EstandarReparacion estandarReparacion;


}
