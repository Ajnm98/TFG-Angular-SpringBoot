package com.example.springboottfg.services.implementations;

import com.example.springboottfg.models.DatosUsuario;
import com.example.springboottfg.repository.DatosUsuarioRepository;
import com.example.springboottfg.services.DatosUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class DatosUsuarioServiceImpl implements DatosUsuarioService {

    @Autowired
    DatosUsuarioRepository datosUsuarioRepository;

    @Override
    public DatosUsuario createDatosUsuario(DatosUsuario datosUsuario) {
        datosUsuario.setDni(datosUsuario.getDni().toUpperCase());
        return datosUsuarioRepository.save(datosUsuario);
    }

    @Override
    public DatosUsuario getDatosUsuarioByIdUser(Long id) {
        return datosUsuarioRepository.findDatosUsuariosByUsuario_Id(id);
    }

    @Override
    public DatosUsuario updateDatosUsuarioByIdUser(DatosUsuario datosUsuario) {
        DatosUsuario datosUsuario1 = datosUsuarioRepository.findDatosUsuariosByUsuario_Id(datosUsuario.getId());
        datosUsuario1.setNombre(datosUsuario.getNombre());
        datosUsuario1.setApellidos(datosUsuario.getApellidos());
        datosUsuario1.setDni(datosUsuario.getDni().toUpperCase());
        datosUsuario1.setDireccion(datosUsuario.getDireccion());
        datosUsuario1.setTelefono(datosUsuario.getTelefono());

        return datosUsuarioRepository.save(datosUsuario1);
    }

}
