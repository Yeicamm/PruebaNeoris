package com.prueba.neoris.service.impl;

import com.prueba.neoris.dto.CrearUsuarioDTO;
import com.prueba.neoris.entity.Cliente;
import com.prueba.neoris.repository.ClienteRepository;
import com.prueba.neoris.service.CrearUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CrearUsuarioServiceImpl implements CrearUsuarioService {

    @Autowired
    private ClienteRepository clienteRepository;

    public CrearUsuarioServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    private CrearUsuarioDTO converEntityToDto(Cliente cliente){
        CrearUsuarioDTO crearUsuarioDTO = new CrearUsuarioDTO();
        crearUsuarioDTO.setId(cliente.getClienteid());
        crearUsuarioDTO.setNombre(cliente.getNombre());
        crearUsuarioDTO.setDireccion(cliente.getDireccion());
        crearUsuarioDTO.setTelefono(cliente.getTelefono());
        crearUsuarioDTO.setContrasena(cliente.getContrasena());
        crearUsuarioDTO.setEstado(cliente.getEstado());
        return crearUsuarioDTO;
    }

    @Override
    public List<CrearUsuarioDTO> obtenerTodos() throws Exception {
        return clienteRepository.findAll()
                .stream()
                .map(this::converEntityToDto)
                .collect(Collectors.toList());
    }
    @Override
    public Optional<CrearUsuarioDTO> obtenerPorId(Long id) throws Exception {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            Cliente clienteNew = cliente.get();
            CrearUsuarioDTO crearUsuarioDTO = converEntityToDto(clienteNew);
            return Optional.of(crearUsuarioDTO);
        } else {
            return Optional.empty();
        }
    }
    @Override
    public CrearUsuarioDTO crearUsuario(CrearUsuarioDTO crearUsuarioDTO) throws Exception{
        try {
            Cliente cliente = new Cliente();
            cliente.setClienteid(crearUsuarioDTO.getId());
            cliente.setNombre(crearUsuarioDTO.getNombre());
            cliente.setDireccion(crearUsuarioDTO.getDireccion());
            cliente.setTelefono(crearUsuarioDTO.getTelefono());
            cliente.setContrasena(crearUsuarioDTO.getContrasena());
            cliente.setEstado(crearUsuarioDTO.getEstado());
            cliente = clienteRepository.save(cliente);
            return converEntityToDto(cliente);
        } catch (Exception e) {
            throw new Exception("Error al crear el usuario: " + e.getMessage());
        }
    }
    @Override
    public Optional<CrearUsuarioDTO> actualizarUsuario(Long id, CrearUsuarioDTO crearUsuarioDTO) throws Exception {
        try {
            Optional<Cliente> clienteOptional = clienteRepository.findById(id);
            if (clienteOptional.isPresent()) {
                Cliente cliente = clienteOptional.get();
                cliente.setClienteid(crearUsuarioDTO.getId());
                cliente.setNombre(crearUsuarioDTO.getNombre());
                cliente.setDireccion(crearUsuarioDTO.getDireccion());
                cliente.setTelefono(crearUsuarioDTO.getTelefono());
                cliente.setContrasena(crearUsuarioDTO.getContrasena());
                cliente.setEstado(crearUsuarioDTO.getEstado());
                cliente = clienteRepository.save(cliente);
                return Optional.of(converEntityToDto(cliente));
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new Exception("Error al actualizar el usuario: " + e.getMessage());
        }
    }
    @Override
    public void eliminarUsuario(Long id) throws Exception {
        try {
            Optional<Cliente> cliente = clienteRepository.findById(id);
            if (cliente.isPresent()) {
                clienteRepository.delete(cliente.get());
            } else {
                throw new Exception("No se ha encontrado el usuario con id: " + id);
            }
        } catch (Exception e) {
            throw new Exception("Error al eliminar el usuario con id " + id + ": " + e.getMessage());
        }
    }
}
