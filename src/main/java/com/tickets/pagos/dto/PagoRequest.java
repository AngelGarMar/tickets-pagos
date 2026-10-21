package com.tickets.pagos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class PagoRequest implements Serializable {
    @NotNull(message = "El campo viajeId no debe estar vacío.")
    private Integer viajeId;
    @NotNull(message = "El campo clienteId no debe estar vacío.")
    private Integer clienteId;
    @NotNull(message = "El campo usuarioId no debe estar vacío.")
    private Integer usuarioId;
    @NotBlank(message = "El campo costo no debe estar vacío.")
    private String costo;
}
