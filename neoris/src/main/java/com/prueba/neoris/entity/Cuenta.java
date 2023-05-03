package com.prueba.neoris.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Table(name = "cuenta")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cuentaid;
    @Column(name = "numeroCuenta")
    private Long numeroCuenta;
    @Column(name = "tipocuenta")
    private String tipoCuenta;
    @Column(name = "saldoinicial")
    private Double saldoInicial;
    @Column(name = "estado")
    private Boolean estado;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @OneToMany(mappedBy = "cuenta")
    private List<Movimiento> movimientos;

}
