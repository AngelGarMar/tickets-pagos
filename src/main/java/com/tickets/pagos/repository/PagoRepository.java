package com.tickets.pagos.repository;

import com.tickets.pagos.entities.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoRepository extends JpaRepository<Pago, Integer> {
}
