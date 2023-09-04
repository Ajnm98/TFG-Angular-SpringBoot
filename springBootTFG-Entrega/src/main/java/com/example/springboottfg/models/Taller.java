package com.example.springboottfg.models;

import lombok.*;

import javax.persistence.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "taller")
public class Taller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_taller")
    private String nombre;

    @Column(name = "direccion")
    private String direccion;

}
