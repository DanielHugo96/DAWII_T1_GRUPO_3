package com.paygo.recargas.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RecargaRequest {
    private String idTarjeta;
    private BigDecimal montoRecarga;
}
