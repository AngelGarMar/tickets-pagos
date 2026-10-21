package com.tickets.pagos.listener;

import com.tickets.pagos.dto.PagoRequest;
import com.tickets.pagos.event.PagoErrorEvent;
import com.tickets.pagos.event.ViajePlacedEvent;
import com.tickets.pagos.services.PagoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
@RefreshScope
public class ViajeEventsListener { //para el rabbit
    private final PagoService pagoService;
    private final RabbitTemplate rabbitTemplate;

    @Value("${app.maintenance.createPago}")
    private boolean createPago;

    @RabbitListener(queues = "viaje-queue") //aqui estamos escuchando esta cola
    public void handleViajePlacedEvent(ViajePlacedEvent event) {
        log.info("viaje recibido en pagos. viaje {}, cliente {}, usuario {} y costo {}", event.viajeId(), event.clienteId(), event.usuarioId(), event.costo());
        PagoRequest pagoRequest = new PagoRequest();
        pagoRequest.setViajeId(event.viajeId());
        pagoRequest.setClienteId(event.clienteId());
        pagoRequest.setUsuarioId(event.usuarioId());
        pagoRequest.setCosto(event.costo());
        log.info("se revisa si se puede efectuar el pago");
        if (!createPago) {
            log.info("por configuración no se puede crear el pago");
            cancelPago(event, "por configuración no se puede crear el pago");
            return;
        }
        try {
            pagoService.createPago(pagoRequest);
            rabbitTemplate.convertAndSend("viaje-events", "viaje.confirmed", event);
            log.info("pago confirmado");
        } catch (Exception e) {
            log.error("Error al recibir el viaje en pagos para el viajeId {}: {}", event.viajeId(), e.getMessage());
            cancelPago(event, "Error interno");
        }
    }

    public void cancelPago(ViajePlacedEvent event, String reason) {
        PagoErrorEvent cancelledEvent = new PagoErrorEvent(event.viajeId(), reason);
        rabbitTemplate.convertAndSend("viaje-events", "viaje.cancelled", cancelledEvent);
    }
}
