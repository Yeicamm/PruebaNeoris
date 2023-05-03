package com.prueba.neoris.controller;

import com.prueba.neoris.dto.ReporteDTO;
import com.prueba.neoris.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReporteController {
    @Autowired
    private ReporteService reporteService;

    @GetMapping("/reporte")
    public ResponseEntity<List<ReporteDTO>> generarReporte(Long id) throws Exception {
        List<ReporteDTO> reporte = reporteService.generarReporte(id);
        return new ResponseEntity<>(reporte, HttpStatus.OK);
    }
}
