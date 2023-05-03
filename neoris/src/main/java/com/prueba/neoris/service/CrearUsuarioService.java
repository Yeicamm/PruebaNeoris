package com.prueba.neoris.service;

import com.prueba.neoris.dto.CrearCuentaUsuarioDTO;
import com.prueba.neoris.dto.CrearUsuarioDTO;
import com.prueba.neoris.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface CrearUsuarioService {

    List<CrearUsuarioDTO> obtenerTodos() throws Exception;
    Optional<CrearUsuarioDTO> obtenerPorId(Long id) throws Exception;
    CrearUsuarioDTO crearUsuario(CrearUsuarioDTO crearUsuarioDTO) throws Exception;
    Optional<CrearUsuarioDTO> actualizarUsuario(Long id, CrearUsuarioDTO crearUsuarioDTO) throws Exception;
    void eliminarUsuario(Long id) throws Exception;
}
