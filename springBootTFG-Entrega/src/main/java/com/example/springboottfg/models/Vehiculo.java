package com.example.springboottfg.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity
@Table(name = "vehiculo")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_vehiculo", length = 20)
    private TiposVehiculos tipoVehiculo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_combustible",length = 20)
    private Combustible tipoCombustible;

    @Column(name = "modelo_vehiculo",length = 60)
    private String modeloVehiculo;

    @Column(name = "fecha_creacion")
    private Date fecha_creacion;

    @Size(max = 8)
    @Column(name = "matricula")
    private String matricula;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario id_usuario;

}
