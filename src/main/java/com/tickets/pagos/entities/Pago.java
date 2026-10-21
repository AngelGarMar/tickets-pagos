package com.tickets.pagos.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "pagos")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String costo;
    @NotBlank
    private String pago;
    @NotBlank
    private String cambio;
    @NotNull
    private Integer estatus;
    private Timestamp creado;
    @NotNull
    private Integer viajeId;
    @NotNull
    private Integer clienteId;
    @NotNull
    private Integer usuarioId;
}
