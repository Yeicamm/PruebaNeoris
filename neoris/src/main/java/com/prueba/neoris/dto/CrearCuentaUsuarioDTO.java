package com.prueba.neoris.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrearCuentaUsuarioDTO {

    private Long id;
    private Long numeroCuenta;
    private String tipo;
    private Double saldoInicial;
    private Boolean estado;
    private String nombre;

}
