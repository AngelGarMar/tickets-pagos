package com.tickets.pagos.event;

import lombok.Builder;

@Builder
public record PagoConfirmedEvent(Integer viajeId) {
}
