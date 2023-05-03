package com.prueba.neoris.service;

import com.prueba.neoris.dto.ReporteDTO;
import com.prueba.neoris.entity.Movimiento;

import java.util.List;

public interface ReporteService {
    List<ReporteDTO> generarReporte(Long id) throws Exception;

}
