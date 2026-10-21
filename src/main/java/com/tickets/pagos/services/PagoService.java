package com.tickets.pagos.services;

import com.tickets.pagos.dto.PagoRequest;
import com.tickets.pagos.event.ViajePlacedEvent;
import com.tickets.pagos.models.ResponseService;

public interface PagoService {
    ResponseService createPago(PagoRequest request);
}
