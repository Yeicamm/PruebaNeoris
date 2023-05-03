package com.prueba.neoris.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.prueba.neoris.entity.Persona;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Table(name = "cliente")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends Persona implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clienteid;
    @Column(name = "contrasena")
    private String contrasena;
    @Column(name = "estado")
    private Boolean estado;
    @OneToMany(mappedBy = "cliente")
    private List<Cuenta> cuentas;
    @OneToMany(mappedBy = "cliente")
    private List<Movimiento> movimientos;

}
