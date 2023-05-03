package com.prueba.neoris.service.impl;

import com.prueba.neoris.dto.CrearCuentaUsuarioDTO;
import com.prueba.neoris.entity.Cliente;
import com.prueba.neoris.entity.Cuenta;
import com.prueba.neoris.repository.CuentaRepository;
import com.prueba.neoris.service.CrearCuentaUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CrearCuentaUsuarioServiceImpl implements CrearCuentaUsuarioService {

    @Autowired
    private CuentaRepository cuentaRepository;

    public CrearCuentaUsuarioServiceImpl(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }
    private CrearCuentaUsuarioDTO converEntityToDto(Cuenta cuenta){
        CrearCuentaUsuarioDTO crearCuentaUsuarioDTO = new CrearCuentaUsuarioDTO();
        crearCuentaUsuarioDTO.setId(cuenta.getCuentaid());
        crearCuentaUsuarioDTO.setNumeroCuenta(cuenta.getNumeroCuenta());
        crearCuentaUsuarioDTO.setTipo(cuenta.getTipoCuenta());
        crearCuentaUsuarioDTO.setSaldoInicial(cuenta.getSaldoInicial());
        crearCuentaUsuarioDTO.setEstado(cuenta.getEstado());
        crearCuentaUsuarioDTO.setNombre(cuenta.getCliente().getNombre());
        return crearCuentaUsuarioDTO;
    }
    @Override
    public List<CrearCuentaUsuarioDTO> obtenerTodos() throws Exception {
        var data = cuentaRepository.findAll()
                .stream()
                .map(this::converEntityToDto)
                .collect(Collectors.toList());
        return data;
    }

    @Override
    public Optional<CrearCuentaUsuarioDTO> obtenerPorId(Long id) throws Exception {
        Optional<Cuenta> cuenta = cuentaRepository.findById(id);

        if (cuenta.isPresent()) {
            Cuenta cuentaNew = cuenta.get();
            CrearCuentaUsuarioDTO crearCuentaUsuarioDTO = converEntityToDto(cuentaNew);
            return Optional.of(crearCuentaUsuarioDTO);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public CrearCuentaUsuarioDTO crearCuenta(CrearCuentaUsuarioDTO crearCuentaUsuarioDTO) throws Exception{
        try {
            Cliente cliente = new Cliente();
            cliente.setClienteid(1L);
            Cuenta cuenta = new Cuenta();
            cuenta.setCliente(cliente);
            cuenta.setCuentaid(crearCuentaUsuarioDTO.getId());
            cuenta.setNumeroCuenta(crearCuentaUsuarioDTO.getNumeroCuenta());
            cuenta.setTipoCuenta(crearCuentaUsuarioDTO.getTipo());
            cuenta.setSaldoInicial(crearCuentaUsuarioDTO.getSaldoInicial());
            cuenta.setEstado(crearCuentaUsuarioDTO.getEstado());
            cuenta.getCliente().setNombre(crearCuentaUsuarioDTO.getNombre());
            cuenta = cuentaRepository.save(cuenta);
            return converEntityToDto(cuenta);
        } catch (Exception e) {
            throw new Exception("Error al crear la cuenta de usuario: " + e.getMessage());
        }
    }


    @Override
    public Optional<CrearCuentaUsuarioDTO> actualizarCuenta(Long id, CrearCuentaUsuarioDTO crearCuentaUsuarioDTO) throws Exception {
        try {
            Optional<Cuenta> cuentaOptional = cuentaRepository.findById(id);
            if (cuentaOptional.isPresent()) {
                Cliente cliente = new Cliente();
                cliente.setClienteid(1L);
                Cuenta cuenta = new Cuenta();
                cuenta.setCliente(cliente);
                cuenta.setCuentaid(crearCuentaUsuarioDTO.getId());
                cuenta.setNumeroCuenta(crearCuentaUsuarioDTO.getNumeroCuenta());
                cuenta.setTipoCuenta(crearCuentaUsuarioDTO.getTipo());
                cuenta.setSaldoInicial(crearCuentaUsuarioDTO.getSaldoInicial());
                cuenta.setEstado(crearCuentaUsuarioDTO.getEstado());
                cuenta.getCliente().setNombre(crearCuentaUsuarioDTO.getNombre());
                cuenta = cuentaRepository.save(cuenta);
                return Optional.of(converEntityToDto(cuenta));
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new Exception("Error al actualizar la cuenta de usuario: " + e.getMessage());
        }
    }
    @Override
    public void eliminarCuenta(Long id) throws Exception {
        try {
            Optional<Cuenta> Cuenta = cuentaRepository.findById(id);
            if (Cuenta.isPresent()) {
                cuentaRepository.delete(Cuenta.get());
            } else {
                throw new Exception("No se ha encontrado la cuenta con id: " + id);
            }
        } catch (Exception e) {
            throw new Exception("Error al eliminar la cuenta de usuario con id " + id + ": " + e.getMessage());
        }
    }
}
