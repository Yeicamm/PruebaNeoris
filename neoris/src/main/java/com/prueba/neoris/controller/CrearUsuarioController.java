package com.prueba.neoris.controller;

import com.prueba.neoris.dto.CrearUsuarioDTO;
import com.prueba.neoris.service.CrearUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "http://localhost:4200/")
public class CrearUsuarioController {
    @Autowired
    private CrearUsuarioService crearUsuarioService;

    @GetMapping
    public ResponseEntity<List<CrearUsuarioDTO>> obtenerTodasLasCuentas() throws Exception{
        try {
            List<CrearUsuarioDTO> crearUsuarioDTOS = crearUsuarioService.obtenerTodos();
            return new ResponseEntity<>(crearUsuarioDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<CrearUsuarioDTO> obtenerUsuarioPorId(@PathVariable Long id) {
        try {
            Optional<CrearUsuarioDTO> crearUsuarioDTOS = crearUsuarioService.obtenerPorId(id);
            if (crearUsuarioDTOS.isPresent()) {
                return new ResponseEntity<>(crearUsuarioDTOS.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/crear")
    public ResponseEntity<CrearUsuarioDTO> crearUsuario(@RequestBody CrearUsuarioDTO crearUsuarioDTO) {
        try {
            CrearUsuarioDTO crearUsuarioDTOS = crearUsuarioService.crearUsuario(crearUsuarioDTO);
            return new ResponseEntity<>(crearUsuarioDTOS, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<CrearUsuarioDTO> actualizarCuenta(@PathVariable Long id, @RequestBody CrearUsuarioDTO crearUsuarioDTO) {
        try {
            Optional<CrearUsuarioDTO> crearUsuarioDTOS = crearUsuarioService.actualizarUsuario(id, crearUsuarioDTO);
            if (crearUsuarioDTOS.isPresent()) {
                return new ResponseEntity<>(crearUsuarioDTOS.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<HttpStatus> eliminarUsuario(@PathVariable Long id) {
        try {
            crearUsuarioService.eliminarUsuario(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
