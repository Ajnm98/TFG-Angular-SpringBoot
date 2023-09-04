package com.example.springboottfg.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@JsonIgnoreProperties({"hibernateLazyInitializer"})
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity
@Table(name = "Usuario",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "email")
        })
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "username")
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usuario_role",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    @JsonIgnore
    @OneToMany(mappedBy = "id_usuario", fetch = FetchType.LAZY)
    private List<Vehiculo> id_vehiculo;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario",fetch = FetchType.LAZY)
    private List<Mecanico> mecanico;


    @Enumerated(EnumType.STRING)
    private EProvider provider;


    public Usuario(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }


    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getUsername() {
        return username;
    }

    public List<Vehiculo> getId_vehiculo() {
        return id_vehiculo;

    }

    public List<Mecanico> getMecanico() {
        return mecanico;

    }


}
