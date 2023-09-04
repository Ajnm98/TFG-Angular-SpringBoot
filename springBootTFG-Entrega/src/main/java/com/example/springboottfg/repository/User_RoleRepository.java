package com.example.springboottfg.repository;


import com.example.springboottfg.models.Role;
import com.example.springboottfg.models.Usuario;
import com.example.springboottfg.models.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface User_RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "Delete from user_roles where user_id = :id", nativeQuery = true)
    void eliminarUsuarioRol(long id);
}
