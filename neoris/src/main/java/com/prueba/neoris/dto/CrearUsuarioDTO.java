package com.prueba.neoris.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrearUsuarioDTO {

    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String contrasena;
    private Boolean estado;

}
