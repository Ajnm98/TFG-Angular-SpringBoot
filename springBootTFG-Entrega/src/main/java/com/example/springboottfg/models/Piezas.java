package com.example.springboottfg.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.xml.stream.events.StartDocument;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity
@Table(name = "piezas")
public class Piezas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(max = 25,message = "El nombre no puede tener más de 25 caracteres")
    private String nombre;

    @Length(max = 50, message = "La descripción no puede tener más de 50 caracteres")
    private String descripcion;

    @Column(name = "precio")
    private Double precio;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "proveedor_id")
    private Proveedor proveedor;

}
