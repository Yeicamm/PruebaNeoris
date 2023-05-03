package com.prueba.neoris.service.impl;

import com.prueba.neoris.dto.MovimientosDTO;
import com.prueba.neoris.entity.Cuenta;
import com.prueba.neoris.entity.Movimiento;
import com.prueba.neoris.repository.MovimientoRepository;
import com.prueba.neoris.service.MovimientosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovimientoServiceImpl implements MovimientosService {
    @Autowired
    private MovimientoRepository movimientoRepository;

    public MovimientoServiceImpl(MovimientoRepository movimientoRepository) {
        this.movimientoRepository = movimientoRepository;
    }

    private MovimientosDTO converEntityToDto(Movimiento movimiento){
        MovimientosDTO movimientosDTO = new MovimientosDTO();
        movimientosDTO.setId(movimiento.getMovimiento_id());
        movimientosDTO.setFecha(movimiento.getFecha());
        movimientosDTO.setNumeroCuenta(movimiento.getCuenta().getNumeroCuenta());
        movimientosDTO.setTipoCuenta(movimiento.getCuenta().getTipoCuenta());
        movimientosDTO.setSaldoInicial(movimiento.getCuenta().getSaldoInicial());
        movimientosDTO.setEstado(movimiento.getCuenta().getEstado());
        movimientosDTO.setMovimiento(movimiento.getMovimiento());
        return movimientosDTO;
    }
    public List<MovimientosDTO> obtenerTodos() throws Exception{
        return movimientoRepository.findAll()
                .stream()
                .map(this::converEntityToDto)
                .collect(Collectors.toList());
    }
    @Override
    public Optional<MovimientosDTO> obtenerPorId(Long id) throws Exception {
        Optional<Movimiento> movimiento = movimientoRepository.findById(id);

        if (movimiento.isPresent()) {
            Movimiento movimientoNew = movimiento.get();
            MovimientosDTO movimientosDTO = converEntityToDto(movimientoNew);
            return Optional.of(movimientosDTO);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public MovimientosDTO crearCuenta(MovimientosDTO movimientosDTO) throws Exception{
        try {
            Cuenta cuenta = new Cuenta();
            cuenta.setCuentaid(1L);
            Movimiento movimiento = new Movimiento();
            movimiento.setCuenta(cuenta);
            movimiento.setMovimiento_id(movimientosDTO.getId());
            movimiento.setFecha(movimientosDTO.getFecha());
            movimiento.getCuenta().setNumeroCuenta(movimientosDTO.getNumeroCuenta());
            movimiento.getCuenta().setTipoCuenta(movimientosDTO.getTipoCuenta());
            movimiento.getCuenta().setSaldoInicial(movimientosDTO.getSaldoInicial());
            movimiento.getCuenta().setEstado(movimientosDTO.getEstado());
            movimiento.setMovimiento(movimientosDTO.getMovimiento());
            movimientoRepository.save(movimiento);
            return movimientosDTO;
        } catch (Exception e) {
            throw new Exception("Error al crear el movimiento: " + e.getMessage());
        }

    }

    @Override
    public Optional<MovimientosDTO> actualizarCuenta(Long id, MovimientosDTO movimientosDTO) throws Exception {
        try {
            Optional<Movimiento> movimientoOptional = movimientoRepository.findById(id);
            if (movimientoOptional.isPresent()) {
                Cuenta cuenta = new Cuenta();
                cuenta.setCuentaid(1L);
                Movimiento movimiento = new Movimiento();
                movimiento.setCuenta(cuenta);
                movimiento.setFecha(movimientosDTO.getFecha());
                movimiento.getCuenta().setNumeroCuenta(movimientosDTO.getNumeroCuenta());
                movimiento.getCuenta().setTipoCuenta(movimientosDTO.getTipoCuenta());
                movimiento.getCuenta().setSaldoInicial(movimientosDTO.getSaldoInicial());
                movimiento.getCuenta().setEstado(movimientosDTO.getEstado());
                movimiento.setMovimiento(movimientosDTO.getMovimiento());
                movimientoRepository.save(movimiento);
                return Optional.of(converEntityToDto(movimiento));
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
            Optional<Movimiento> movimiento = movimientoRepository.findById(id);
            if (movimiento.isPresent()) {
                movimientoRepository.delete(movimiento.get());
            } else {
                throw new Exception("No se ha encontrado la cuenta con id: " + id);
            }
        } catch (Exception e) {
            throw new Exception("Error al eliminar la cuenta de usuario con id " + id + ": " + e.getMessage());
        }
    }
}
