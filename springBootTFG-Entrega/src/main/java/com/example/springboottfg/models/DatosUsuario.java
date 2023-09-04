package com.example.springboottfg.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DATOS_USUARIO", uniqueConstraints = {
        @UniqueConstraint(columnNames = "dni")})
public class DatosUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size( min = 9, max = 9)
    @NotBlank
    @Column(name = "dni")
    private String dni;

    @NotBlank
    @Column(name = "nombre")
    private String nombre;

    @NotBlank
    @Column(name = "apellidos")
    private String apellidos;

    @NotBlank
    @Column(name = "direccion")
    private String direccion;

    @NotBlank
    @Column(name = "telefono")
    private String telefono;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
