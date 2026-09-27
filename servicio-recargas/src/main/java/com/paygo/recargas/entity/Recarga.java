package com.paygo.recargas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "recarga")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recarga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRecarga;

    private String idTarjeta;

    private BigDecimal saldoDisponible;

    private BigDecimal montoRecarga;

    private LocalDateTime fechaRecarga;
}
