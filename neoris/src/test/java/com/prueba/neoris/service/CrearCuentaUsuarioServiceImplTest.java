package com.prueba.neoris.service;

import com.prueba.neoris.dto.CrearCuentaUsuarioDTO;
import com.prueba.neoris.entity.Cliente;
import com.prueba.neoris.entity.Cuenta;
import com.prueba.neoris.repository.CuentaRepository;
import com.prueba.neoris.service.impl.CrearCuentaUsuarioServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CrearCuentaUsuarioServiceImplTest {
    @Mock
    private CuentaRepository cuentaRepository;

    @InjectMocks
    private CrearCuentaUsuarioServiceImpl crearCuentaUsuarioService;

    @Test
    public void testObtenerTodos() throws Exception {
        // Creamos dos objetos Cuenta para simular la respuesta del método findAll()
        Cuenta cuenta1 = new Cuenta();
        cuenta1.setCuentaid(1L);
        cuenta1.setNumeroCuenta(1L);
        cuenta1.setTipoCuenta("Ahorro");
        cuenta1.setSaldoInicial(1000.0);
        cuenta1.setEstado(true);
        Cliente cliente1 = new Cliente();
        cliente1.setNombre("Cristian Rojas");
        cuenta1.setCliente(cliente1);

        Cuenta cuenta2 = new Cuenta();
        cuenta2.setCuentaid(2L);
        cuenta2.setNumeroCuenta(2L);
        cuenta2.setTipoCuenta("corriente");
        cuenta2.setSaldoInicial(5000.0);
        cuenta2.setEstado(true);
        Cliente cliente2 = new Cliente();
        cliente2.setNombre("Camilo Rojas");
        cuenta2.setCliente(cliente2);

        List<Cuenta> cuentas = new ArrayList<>();
        cuentas.add(cuenta1);
        cuentas.add(cuenta2);

        // Configuramos el comportamiento del mock cuentaRepository
        when(cuentaRepository.findAll()).thenReturn(cuentas);

        // Invocamos al método que queremos probar
        List<CrearCuentaUsuarioDTO> result = crearCuentaUsuarioService.obtenerTodos();

        // Verificamos que el método findAll() del cuentaRepository fue invocado correctamente
        verify(cuentaRepository).findAll();

        // Verificamos que la lista resultante tiene los elementos esperados
        assertEquals(2, result.size());

        CrearCuentaUsuarioDTO dto1 = result.get(0);
        assertEquals(cuenta1.getCuentaid(), dto1.getId());
        assertEquals(cuenta1.getNumeroCuenta(), dto1.getNumeroCuenta());
        assertEquals(cuenta1.getTipoCuenta(), dto1.getTipo());
        assertEquals(cuenta1.getSaldoInicial(), dto1.getSaldoInicial(), 0.0);
        assertEquals(cuenta1.getEstado(), dto1.getEstado());
        assertEquals(cuenta1.getCliente().getNombre(), dto1.getNombre());

        CrearCuentaUsuarioDTO dto2 = result.get(1);
        assertEquals(cuenta2.getCuentaid(), dto2.getId());
        assertEquals(cuenta2.getNumeroCuenta(), dto2.getNumeroCuenta());
        assertEquals(cuenta2.getTipoCuenta(), dto2.getTipo());
        assertEquals(cuenta2.getSaldoInicial(), dto2.getSaldoInicial(), 0.0);
        assertEquals(cuenta2.getEstado(), dto2.getEstado());
        assertEquals(cuenta2.getCliente().getNombre(), dto2.getNombre());
    }
    @Test
    public void testObtenerPorId() throws Exception {
        Long cuentaid = 1L;
        Cuenta cuentaMock = new Cuenta();
        cuentaMock.setCuentaid(cuentaid);
        Cliente cliente = new Cliente();
        cuentaMock.setCliente(cliente);
        cliente.setClienteid(1L);
        when(cuentaRepository.findById(cuentaid)).thenReturn(Optional.of(cuentaMock));

        // Act
        Optional<CrearCuentaUsuarioDTO> resultado = crearCuentaUsuarioService.obtenerPorId(cuentaid);

        // Assert
        assertTrue(resultado.isPresent());
        assertEquals(cuentaid, resultado.get().getId());
    }
    @Test
    public void testCrearCuenta() throws Exception {
        // Arrange
        CrearCuentaUsuarioDTO crearCuentaUsuarioDTO = new CrearCuentaUsuarioDTO();
        crearCuentaUsuarioDTO.setId(1L);
        crearCuentaUsuarioDTO.setNumeroCuenta(1L);
        crearCuentaUsuarioDTO.setTipo("Ahorros");
        crearCuentaUsuarioDTO.setSaldoInicial(100000.0);
        crearCuentaUsuarioDTO.setEstado(true);
        crearCuentaUsuarioDTO.setNombre("Cristian Rojas");

        Cliente cliente = new Cliente();
        cliente.setClienteid(1L);

        Cuenta cuenta = new Cuenta();
        cuenta.setCliente(cliente);
        cuenta.setCuentaid(1L);
        cuenta.setNumeroCuenta(1L);
        cuenta.setTipoCuenta("Ahorros");
        cuenta.setSaldoInicial(100000.0);
        cuenta.setEstado(true);
        cuenta.getCliente().setNombre("Cristian Rojas");

        Mockito.when(cuentaRepository.save(Mockito.any(Cuenta.class))).thenReturn(cuenta);

        // Act
        CrearCuentaUsuarioDTO resultado = crearCuentaUsuarioService.crearCuenta(crearCuentaUsuarioDTO);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getNumeroCuenta());
        assertEquals("Ahorros", resultado.getTipo());
        assertEquals(100000.0, resultado.getSaldoInicial());
        assertEquals(true, resultado.getEstado());
        assertEquals("Cristian Rojas", resultado.getNombre());
    }

}
