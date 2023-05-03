package com.prueba.neoris.repository;

import com.prueba.neoris.entity.Cliente;
import com.prueba.neoris.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReporteRepository extends JpaRepository<Cliente, Long> {
    @Query(value = "SELECT m.fecha, c.clienteid, cu.cuentaid, cu.tipoCuenta, cu.saldoInicial, cu.estado, m.movimiento_id, m.saldo, (cu.saldoInicial + SUM(m.tipomovimiento)) AS saldoActual " +
            "FROM Movimientos m " +
            "INNER JOIN Cuenta cu ON m.cuenta_id = cu.cuentaid " +
            "INNER JOIN Cliente c ON cu.cliente_id = c.clienteid " +
            "GROUP BY cu.cuentaid, m.fecha " +
            "ORDER BY m.fecha DESC;", nativeQuery = true)
    List<Movimiento> generarReporte(Long id);
}
