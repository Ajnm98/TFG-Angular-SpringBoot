package com.example.springboottfg.repository;

import com.example.springboottfg.models.DatosUsuario;
import com.example.springboottfg.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DatosUsuarioRepository extends JpaRepository<DatosUsuario, Long> {

    DatosUsuario findDatosUsuarioByNombre(String nombre);
    DatosUsuario findDatosUsuariosByUsuario_Id(Long id);

    @Query(value = "select * from datos_usuario where usuario_id = :usuario", nativeQuery = true)
    DatosUsuario obtenerUsuarioid(long usuario);


}
