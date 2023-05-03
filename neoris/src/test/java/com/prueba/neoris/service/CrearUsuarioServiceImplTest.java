package com.prueba.neoris.service;

import com.prueba.neoris.dto.CrearUsuarioDTO;
import com.prueba.neoris.entity.Cliente;
import com.prueba.neoris.repository.ClienteRepository;
import com.prueba.neoris.service.impl.CrearUsuarioServiceImpl;
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

public class CrearUsuarioServiceImplTest {
    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private CrearUsuarioServiceImpl crearUsuarioService;
    @Test
    void testObtenerTodos() throws Exception {
        Cliente cliente1 = new Cliente();
        cliente1.setClienteid(1L);
        cliente1.setNombre("Cristian Rojas");
        cliente1.setDireccion("carrera");
        cliente1.setTelefono("3229098867");
        cliente1.setContrasena("17649912");
        cliente1.setEstado(true);

        Cliente cliente2 = new Cliente();
        cliente2.setClienteid(2L);
        cliente2.setNombre("Pedro");
        cliente2.setDireccion("Calle 2");
        cliente2.setTelefono("987654321");
        cliente2.setContrasena("secret");
        cliente2.setEstado(false);

        List<Cliente> clientes = new ArrayList<>();
        clientes.add(cliente1);
        clientes.add(cliente2);
        when(clienteRepository.findAll()).thenReturn(clientes);

        // When
        List<CrearUsuarioDTO> result = crearUsuarioService.obtenerTodos();

        // Verificamos que el método findAll() del clienteRepository fue invocado correctamente
        verify(clienteRepository).findAll();

        // Verificamos que la lista resultante tiene los elementos esperados
        assertEquals(2, result.size());

        CrearUsuarioDTO dto1 = result.get(0);
        assertEquals(cliente1.getClienteid(), dto1.getId());
        assertEquals(cliente1.getNombre(), dto1.getNombre());
        assertEquals(cliente1.getDireccion(), dto1.getDireccion());
        assertEquals(cliente1.getTelefono(), dto1.getTelefono());
        assertEquals(cliente1.getContrasena(), dto1.getContrasena());
        assertEquals(cliente1.getEstado(), dto1.getEstado());

        CrearUsuarioDTO dto2 = result.get(1 );
        assertEquals(cliente2.getClienteid(), dto2.getId());
        assertEquals(cliente2.getNombre(), dto2.getNombre());
        assertEquals(cliente2.getDireccion(), dto2.getDireccion());
        assertEquals(cliente2.getTelefono(), dto2.getTelefono());
        assertEquals(cliente2.getContrasena(), dto2.getContrasena());
        assertEquals(cliente2.getEstado(), dto2.getEstado());
    }

    @Test
    public void testObtenerPorId() throws Exception {
        Long clienteid = 1L;
        Cliente clienteMock = new Cliente();
        clienteMock.setClienteid(clienteid);
        when(clienteRepository.findById(clienteid)).thenReturn(Optional.of(clienteMock));

        // Act
        Optional<CrearUsuarioDTO> resultado = crearUsuarioService.obtenerPorId(clienteid);

        // Assert
        assertTrue(resultado.isPresent());
        assertEquals(clienteid, resultado.get().getId());
    }
    @Test
    public void testCrearUsuario() throws Exception {
        // Arrange
        CrearUsuarioDTO crearUsuarioDTO = new CrearUsuarioDTO();
        crearUsuarioDTO.setId(1L);
        crearUsuarioDTO.setNombre("Cristian Rojas");
        crearUsuarioDTO.setDireccion("cccc");
        crearUsuarioDTO.setTelefono("3229098867");
        crearUsuarioDTO.setContrasena("1234");
        crearUsuarioDTO.setEstado(true);

        Cliente cliente = new Cliente();
        cliente.setNombre("Cristian Rojas");
        cliente.setDireccion("cccc");
        cliente.setTelefono("3229098867");
        cliente.setContrasena("17649912");
        cliente.setEstado(true);

        Mockito.when(clienteRepository.save(Mockito.any(Cliente.class))).thenReturn(cliente);

        // Act
        CrearUsuarioDTO resultado = crearUsuarioService.crearUsuario(crearUsuarioDTO);

        // Assert
        assertNotNull(resultado);
        assertEquals("Cristian Rojas", resultado.getNombre());
        assertEquals("cccc", resultado.getDireccion());
        assertEquals("3229098867", resultado.getTelefono());
        assertEquals("17649912", resultado.getContrasena());
        assertEquals(true, resultado.getEstado());
    }

}
