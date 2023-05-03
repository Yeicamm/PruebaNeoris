package com.prueba.neoris.service;

import com.prueba.neoris.dto.MovimientosDTO;
import com.prueba.neoris.entity.Cuenta;
import com.prueba.neoris.entity.Movimiento;
import com.prueba.neoris.repository.MovimientoRepository;
import com.prueba.neoris.service.impl.MovimientoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovimientoServiceImplTest {
    @Mock
    private MovimientoRepository movimientoRepository;

    @InjectMocks
    private MovimientoServiceImpl movimientoService;

    @Test
    public void testObtenerTodos() throws Exception {
        // Creamos dos objetos Cuenta para simular la respuesta del método findAll()
        Movimiento movimiento1 = new Movimiento();
        Cuenta cuenta = new Cuenta();
        cuenta.setCuentaid(1L);
        movimiento1.setCuenta(cuenta);
        movimiento1.setMovimiento_id(1L);
        movimiento1.setFecha("2023-05-03");
        movimiento1.getCuenta().setNumeroCuenta(1L);
        movimiento1.getCuenta().setTipoCuenta("Ahorro");
        movimiento1.getCuenta().setSaldoInicial(1000.0);
        movimiento1.getCuenta().setEstado(true);
        movimiento1.setMovimiento(500.0);

        Movimiento movimiento2 = new Movimiento();
        movimiento2.setCuenta(cuenta);
        movimiento2.setMovimiento_id(1L);
        movimiento2.setFecha("2023-05-03");
        movimiento2.getCuenta().setNumeroCuenta(1L);
        movimiento2.getCuenta().setTipoCuenta("Ahorro");
        movimiento2.getCuenta().setSaldoInicial(1000.0);
        movimiento2.getCuenta().setEstado(true);
        movimiento2.setMovimiento(500.0);

        List<Movimiento> movimiento = new ArrayList<>();
        movimiento.add(movimiento1);
        movimiento.add(movimiento2);

        // Configuramos el comportamiento del mock cuentaRepository
        when(movimientoRepository.findAll()).thenReturn(movimiento);

        // Invocamos al método que queremos probar
        List<MovimientosDTO> result = movimientoService.obtenerTodos();

        // Verificamos que el método findAll() del cuentaRepository fue invocado correctamente
        verify(movimientoRepository).findAll();

        // Verificamos que la lista resultante tiene los elementos esperados
        assertEquals(2, result.size());

        MovimientosDTO dto1 = result.get(0);
        assertEquals(movimiento1.getMovimiento_id(), dto1.getId());
        assertEquals(movimiento1.getFecha(),dto1.getFecha());
        assertEquals(movimiento1.getCuenta().getNumeroCuenta(), dto1.getNumeroCuenta());
        assertEquals(movimiento1.getCuenta().getTipoCuenta(), dto1.getTipoCuenta());
        assertEquals(movimiento1.getCuenta().getSaldoInicial(), dto1.getSaldoInicial(), 0.0);
        assertEquals(movimiento1.getCuenta().getEstado(), dto1.getEstado());
        assertEquals(movimiento1.getMovimiento(), dto1.getMovimiento());

        MovimientosDTO dto2 = result.get(1);
        assertEquals(movimiento2.getMovimiento_id(), dto2.getId());
        assertEquals(movimiento2.getFecha(),dto2.getFecha());
        assertEquals(movimiento2.getCuenta().getNumeroCuenta(), dto2.getNumeroCuenta());
        assertEquals(movimiento2.getCuenta().getTipoCuenta(), dto2.getTipoCuenta());
        assertEquals(movimiento2.getCuenta().getSaldoInicial(), dto2.getSaldoInicial(), 0.0);
        assertEquals(movimiento2.getCuenta().getEstado(), dto2.getEstado());
        assertEquals(movimiento2.getMovimiento(), dto2.getMovimiento());
    }
    @Test
    public void testObtenerPorId() throws Exception {
        Long movimientoid = 1L;
        Movimiento movimientoMock = new Movimiento();
        Cuenta cuenta = new Cuenta();
        cuenta.setCuentaid(1L);
        movimientoMock.setCuenta(cuenta);
        movimientoMock.setMovimiento_id(movimientoid);
        when(movimientoRepository.findById(movimientoid)).thenReturn(Optional.of(movimientoMock));

        // Act
        Optional<MovimientosDTO> resultado = movimientoService.obtenerPorId(movimientoid);

        // Assert
        assertTrue(resultado.isPresent());
        assertEquals(movimientoid, resultado.get().getId());
    }
    @Test
    public void testCrearMovimiento() throws Exception {
        // Arrange

        MovimientosDTO movimientosDTO = new MovimientosDTO();
        movimientosDTO.setId(1L);
        movimientosDTO.setFecha("2023-05-03");
        movimientosDTO.setNumeroCuenta(1L);
        movimientosDTO.setTipoCuenta("Ahorros");
        movimientosDTO.setSaldoInicial(100000.0);
        movimientosDTO.setEstado(true);
        movimientosDTO.setMovimiento(500.0);

        Cuenta cuenta = new Cuenta();
        cuenta.setCuentaid(1L);
        Movimiento movimiento = new Movimiento();
        movimiento.setCuenta(cuenta);
        movimiento.setMovimiento_id(1L);
        movimiento.setFecha("2023-05-03");
        movimiento.getCuenta().setNumeroCuenta(1L);
        movimiento.getCuenta().setTipoCuenta("Ahorros");
        movimiento.getCuenta().setSaldoInicial(100000.0);
        movimiento.getCuenta().setEstado(true);
        movimiento.setMovimiento(500.0);

        Mockito.when(movimientoRepository.save(Mockito.any(Movimiento.class))).thenReturn(movimiento);

        // Act
        MovimientosDTO resultado = movimientoService.crearCuenta(movimientosDTO);

        // Assert
        assertNotNull(resultado);
        assertEquals("2023-05-03", resultado.getFecha());
        assertEquals(1L, resultado.getNumeroCuenta());
        assertEquals("Ahorros", resultado.getTipoCuenta());
        assertEquals(100000.0, resultado.getSaldoInicial());
        assertEquals(true, resultado.getEstado());
        assertEquals(500.0, resultado.getMovimiento());
    }

}
