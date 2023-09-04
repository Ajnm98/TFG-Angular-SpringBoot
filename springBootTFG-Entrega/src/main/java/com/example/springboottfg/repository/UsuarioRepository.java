package com.example.springboottfg.repository;

import com.example.springboottfg.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Usuario findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query(value = "select usuario_id from vehiculo where id = :usuario", nativeQuery = true)
    Integer obtenerUsuario(long usuario);
    @Query(value = "select * from Usuario where id = :usuario", nativeQuery = true)
    Usuario obtenerUsuarioid(long usuario);

    @Modifying
    @Transactional
    @Query("update Usuario c set c.email = :email, c.username = :username, c.password = :password where c.id = :id")
    void editarUsuario(@Param("username") String username, @Param("password") String password,
                       @Param("id") Long id, @Param("email") String email);


    @Query(value = "select * from Usuario where email = :email", nativeQuery = true)
    Usuario findByemail(String email);


}
