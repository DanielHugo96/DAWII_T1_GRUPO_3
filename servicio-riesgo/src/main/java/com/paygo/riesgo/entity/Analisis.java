package com.paygo.riesgo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "analisis")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Analisis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idRecarga;

    private String idTarjeta;

    private BigDecimal saldoDisponible;

    private BigDecimal montoRecarga;

    private LocalDateTime fechaRecarga;

    private String situacion; // "Aprobada" u "Observada"
}
