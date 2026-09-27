package com.paygo.tarjetas.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "tarjeta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarjeta {

    @Id
    private String idTarjeta;

    private String nomTitular;

    private BigDecimal saldoAsignado;

    private BigDecimal saldoDisponible;
}
