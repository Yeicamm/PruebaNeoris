package com.prueba.neoris.service;

import com.prueba.neoris.dto.CrearCuentaUsuarioDTO;

import java.util.List;
import java.util.Optional;


public interface CrearCuentaUsuarioService {
    List<CrearCuentaUsuarioDTO> obtenerTodos() throws Exception;
    Optional<CrearCuentaUsuarioDTO> obtenerPorId(Long id) throws Exception;
    CrearCuentaUsuarioDTO crearCuenta(CrearCuentaUsuarioDTO cuentaUsuarioDTO) throws Exception;
    Optional<CrearCuentaUsuarioDTO> actualizarCuenta(Long id, CrearCuentaUsuarioDTO crearCuentaUsuarioDTO) throws Exception;
    void eliminarCuenta(Long id) throws Exception;
}
