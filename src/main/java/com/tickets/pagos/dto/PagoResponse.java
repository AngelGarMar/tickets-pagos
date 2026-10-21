package com.tickets.pagos.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PagoResponse implements Serializable {
    private Integer id;
    private String costo;
    private String cambio;
}
