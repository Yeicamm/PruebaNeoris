package com.prueba.neoris.controller;

import com.prueba.neoris.dto.CrearCuentaUsuarioDTO;
import com.prueba.neoris.dto.CrearUsuarioDTO;
import com.prueba.neoris.service.CrearCuentaUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cuentas")
@CrossOrigin(origins = "http://localhost:4200/")
public class CrearCuentaUsuarioController {

    @Autowired
    private CrearCuentaUsuarioService crearCuentaUsuarioService;

    @GetMapping
    public ResponseEntity<List<CrearCuentaUsuarioDTO>> obtenerTodasLasCuentas() throws Exception{
        try {
            List<CrearCuentaUsuarioDTO> crearCuentaUsuarioDTOS = crearCuentaUsuarioService.obtenerTodos();
            return new ResponseEntity<>(crearCuentaUsuarioDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<CrearCuentaUsuarioDTO> obtenerCuentaPorId(@PathVariable Long id) {
        try {
            Optional<CrearCuentaUsuarioDTO> crearCuentaUsuarioDTOS = crearCuentaUsuarioService.obtenerPorId(id);
            if (crearCuentaUsuarioDTOS.isPresent()) {
                return new ResponseEntity<>(crearCuentaUsuarioDTOS.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/crear")
    public ResponseEntity<CrearCuentaUsuarioDTO> crearCuenta(@RequestBody CrearCuentaUsuarioDTO crearCuentaUsuarioDTO) {
        try {
            CrearCuentaUsuarioDTO crearCuentaUsuarioDTOS = crearCuentaUsuarioService.crearCuenta(crearCuentaUsuarioDTO);
            return new ResponseEntity<>(crearCuentaUsuarioDTOS, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<CrearCuentaUsuarioDTO> actualizarCuenta(@PathVariable Long id, @RequestBody CrearCuentaUsuarioDTO crearCuentaUsuarioDTO) {
        try {
            Optional<CrearCuentaUsuarioDTO> crearCuentaUsuarioDTOS = crearCuentaUsuarioService.actualizarCuenta(id, crearCuentaUsuarioDTO);
            if (crearCuentaUsuarioDTOS.isPresent()) {
                return new ResponseEntity<>(crearCuentaUsuarioDTOS.get(), HttpStatus.OK);
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
            crearCuentaUsuarioService.eliminarCuenta(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


