package com.prueba.neoris.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "persona")
@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Persona {

    @Column(name = "personaid")
    private Long personaid;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "genero")
    private String genero;
    @Column(name = "edad")
    private Integer edad;
    @Column(name = "identificacion")
    private String identificacion;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private String telefono;

}
