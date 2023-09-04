package com.example.springboottfg.services;

import com.example.springboottfg.models.DatosUsuario;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface DatosUsuarioService {

    DatosUsuario createDatosUsuario(DatosUsuario datosUsuario);
    DatosUsuario updateDatosUsuarioByIdUser(DatosUsuario datosUsuario);
    DatosUsuario getDatosUsuarioByIdUser(Long id);

}
