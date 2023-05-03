package com.prueba.neoris.service.impl;

import com.prueba.neoris.dto.ReporteDTO;
import com.prueba.neoris.entity.Cliente;
import com.prueba.neoris.entity.Cuenta;
import com.prueba.neoris.entity.Movimiento;
import com.prueba.neoris.repository.MovimientoRepository;
import com.prueba.neoris.repository.ReporteRepository;
import com.prueba.neoris.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteServiceImpl implements ReporteService{

    @Autowired
    private ReporteRepository reporteRepository;

    public ReporteServiceImpl(ReporteRepository reporteRepository) {
        this.reporteRepository = reporteRepository;
    }

    private ReporteDTO converEntityToDto(Movimiento movimiento) {
        ReporteDTO reporteDTO = new ReporteDTO();
        reporteDTO.setFecha(movimiento.getFecha());
        reporteDTO.setNombre(movimiento.getCliente().getNombre());
        reporteDTO.setNumeroCuenta(movimiento.getCuenta().getNumeroCuenta());
        reporteDTO.setTipoCuenta(movimiento.getCuenta().getTipoCuenta());
        reporteDTO.setSaldoInicial(movimiento.getCuenta().getSaldoInicial());
        reporteDTO.setEstado(movimiento.getCuenta().getEstado());
        reporteDTO.setMovimiento(movimiento.getMovimiento());
        reporteDTO.setSaldoActual(movimiento.getSaldo());
        return reporteDTO;

    }
    @Override
    public List<ReporteDTO> generarReporte(Long id) throws Exception{
        return reporteRepository.generarReporte(id)
                .stream()
                .map(this::converEntityToDto)
                .collect(Collectors.toList());
    }

}