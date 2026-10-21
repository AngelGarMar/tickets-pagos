package com.tickets.pagos.event;

public record ViajePlacedEvent(
        Integer viajeId,
        Integer clienteId,
        Integer usuarioId,
        String costo
) {

}
