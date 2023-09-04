package com.example.springboottfg.models;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity
@Table(name = "perfil_usuario")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(max = 25,message = "El nombre no puede tener más de 25 caracteres")
    private String nombre;

    @Length(max = 50, message = "La dirección no puede tener más de 50 caracteres")
    private String dirección;

    @Length(max = 50,message = "El email no puede tener más de 50 caracteres")
    private String email;

    @Length(max = 25, message = "El teléfono no puede tener más de 25 caracteres")
    private String telefono;


    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDirección() {
        return dirección;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }


}