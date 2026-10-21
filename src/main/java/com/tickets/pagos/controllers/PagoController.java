package com.tickets.pagos.controllers;

import com.tickets.pagos.constants.Constants;
import com.tickets.pagos.dto.PagoRequest;
import com.tickets.pagos.models.ResponseService;
import com.tickets.pagos.services.PagoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/tickets/pagos")
public class PagoController {
    @Autowired
    private PagoService service;

    @PostMapping
    public ResponseEntity<ResponseService> createPago(@RequestBody @Valid PagoRequest request, BindingResult result) {
        if (result.hasFieldErrors()) return validation(result);
        ResponseService response = service.createPago(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private ResponseEntity<ResponseService> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), err.getDefaultMessage());
        });
        return new ResponseEntity<>(new ResponseService(Constants.RESPONSE_TYPE_ERROR, Constants.MISSED_INFORMATION, errors), HttpStatus.OK);
    }
}
