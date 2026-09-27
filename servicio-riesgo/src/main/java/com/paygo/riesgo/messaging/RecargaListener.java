package com.paygo.riesgo.messaging;

import com.paygo.riesgo.dto.RecargaMensajeDTO;
import com.paygo.riesgo.entity.Analisis;
import com.paygo.riesgo.repository.AnalisisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class RecargaListener {

    private final AnalisisRepository analisisRepository;

    @RabbitListener(queues = "${app.queue.name}")
    public void recibirRecarga(RecargaMensajeDTO mensaje) {
        // Regla de negocio:
        // Aprobada: monto_recarga <= 70% del saldo_disponible
        // Observada: monto_recarga > 70% del saldo_disponible
        BigDecimal limite = mensaje.getSaldoDisponible().multiply(BigDecimal.valueOf(0.70));
        String situacion = mensaje.getMontoRecarga().compareTo(limite) > 0 ? "Observada" : "Aprobada";

        Analisis analisis = new Analisis();
        analisis.setIdRecarga(mensaje.getIdRecarga());
        analisis.setIdTarjeta(mensaje.getIdTarjeta());
        analisis.setSaldoDisponible(mensaje.getSaldoDisponible());
        analisis.setMontoRecarga(mensaje.getMontoRecarga());
        analisis.setFechaRecarga(mensaje.getFechaRecarga());
        analisis.setSituacion(situacion);

        analisisRepository.save(analisis);
    }
}
