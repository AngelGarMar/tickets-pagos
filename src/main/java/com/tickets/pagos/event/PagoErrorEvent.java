package com.tickets.pagos.event;

public record PagoErrorEvent(Integer viajeId, String reason) {
}
