package com.tickets.pagos.services.impl;

import com.tickets.pagos.constants.Constants;
import com.tickets.pagos.dto.PagoRequest;
import com.tickets.pagos.dto.PagoResponse;
import com.tickets.pagos.entities.Pago;
import com.tickets.pagos.models.ResponseService;
import com.tickets.pagos.repository.PagoRepository;
import com.tickets.pagos.services.PagoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@Slf4j
public class PagoServiceImpl implements PagoService {
    @Autowired
    private PagoRepository repository;

    @Override
    @Transactional
    public ResponseService createPago(PagoRequest request) {
        log.info("se comienza a guardar el pago");
        Pago pago = Pago.builder()
                .costo(request.getCosto())
                .pago("0")
                .cambio("0")
                .estatus(1)
                .creado(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("America/Mexico_City"))))
                .viajeId(request.getViajeId())
                .clienteId(request.getClienteId())
                .usuarioId(request.getUsuarioId())
                .build();
        Pago pagoSaved = repository.save(pago);
        PagoResponse pagoResponse = new PagoResponse();
        pagoResponse.setId(pagoSaved.getId());
        pagoResponse.setCosto(pagoSaved.getCosto());
        pagoResponse.setCambio(pagoSaved.getCambio());
        log.info("se regresa la respuesta");
        return new ResponseService(Constants.RESPONSE_TYPE_SUCCESS, Constants.PAGO_SAVED, pagoResponse);
    }
}
