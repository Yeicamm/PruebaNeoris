package com.prueba.neoris.service;

import com.prueba.neoris.dto.CrearCuentaUsuarioDTO;
import com.prueba.neoris.dto.MovimientosDTO;

import java.util.List;
import java.util.Optional;

public interface MovimientosService {

    List<MovimientosDTO> obtenerTodos() throws Exception;
    Optional<MovimientosDTO> obtenerPorId(Long id) throws Exception;
    MovimientosDTO crearCuenta(MovimientosDTO movimientosDTO) throws Exception;
    Optional<MovimientosDTO> actualizarCuenta(Long id, MovimientosDTO movimientosDTO) throws Exception;
    void eliminarCuenta(Long id) throws Exception;
}
