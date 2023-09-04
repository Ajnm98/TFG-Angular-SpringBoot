package com.example.springboottfg.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity
@Table( name = "mecanico")
public class Mecanico {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String apellidos;

    private String especialidad;

    private String contrasenia;

    private String email;

    @Column(name = "identificador")
    private String identificacion;


    @JsonIgnore
    @OneToMany(mappedBy = "id_mecanico",fetch = FetchType.LAZY)
    private List<Cita> id_cita;

    @JsonIgnoreProperties(ignoreUnknown = true,
            value = {"hibernateLazyInitializer", "handler", "created"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
