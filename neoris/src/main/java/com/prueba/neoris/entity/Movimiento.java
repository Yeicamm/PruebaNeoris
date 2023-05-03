package com.prueba.neoris.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name = "movimientos")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movimiento_id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha")
    private String fecha;
    @Column(name = "tipomovimiento")
    private Double movimiento;
    @Column(name = "valor")
    private Double valor;
    @Column(name = "saldo")
    private Double saldo;
    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;


}
