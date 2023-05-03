package com.prueba.neoris.controller;

import com.prueba.neoris.dto.CrearCuentaUsuarioDTO;
import com.prueba.neoris.dto.MovimientosDTO;
import com.prueba.neoris.service.CrearCuentaUsuarioService;
import com.prueba.neoris.service.MovimientosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movimientos")
@CrossOrigin(origins = "http://localhost:4200/")
public class CrearMovimientoController {

    @Autowired
    private MovimientosService movimientosService;

    @GetMapping
    public ResponseEntity<List<MovimientosDTO>> obtenerTodos() throws Exception{
        try {
            List<MovimientosDTO> movimientosDTOS = movimientosService.obtenerTodos();
            return new ResponseEntity<>(movimientosDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<MovimientosDTO> obtenerCuentaPorId(@PathVariable Long id) {
        try {
            Optional<MovimientosDTO> movimientosDTO = movimientosService.obtenerPorId(id);
            if (movimientosDTO.isPresent()) {
                return new ResponseEntity<>(movimientosDTO.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/crear")
    public ResponseEntity<MovimientosDTO> crearCuenta(@RequestBody MovimientosDTO movimientosDTO) {
        try {
            MovimientosDTO movimientosDTOS = movimientosService.crearCuenta(movimientosDTO);
            return new ResponseEntity<>(movimientosDTOS, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<MovimientosDTO> actualizarCuenta(@PathVariable Long id, @RequestBody MovimientosDTO movimientosDTO) {
        try {
            Optional<MovimientosDTO> movimientosDTOS = movimientosService.actualizarCuenta(id, movimientosDTO);
            if (movimientosDTOS.isPresent()) {
                return new ResponseEntity<>(movimientosDTOS.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<HttpStatus> eliminarCuenta(@PathVariable Long id) {
        try {
            movimientosService.eliminarCuenta(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
