package com.paygo.recargas.service;

import com.paygo.recargas.client.TarjetaClient;
import com.paygo.recargas.dto.TarjetaDTO;
import com.paygo.recargas.entity.Recarga;
import com.paygo.recargas.messaging.RecargaPublisher;
import com.paygo.recargas.repository.RecargaRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RecargaService {

    private final RecargaRepository recargaRepository;
    private final TarjetaClient tarjetaClient;
    private final RecargaPublisher recargaPublisher;

    public Recarga registrarRecarga(String idTarjeta, BigDecimal montoRecarga) {
        // 1. Comunicacion sincronica con servicio de Tarjetas (OpenFeign)
        TarjetaDTO tarjeta;
        try {
            tarjeta = tarjetaClient.buscarPorId(idTarjeta);
        } catch (FeignException.NotFound e) {
            throw new IllegalArgumentException("La tarjeta con id " + idTarjeta + " no existe");
        }

        if (tarjeta == null) {
            throw new IllegalArgumentException("La tarjeta con id " + idTarjeta + " no existe");
        }

        // 2. Se arma la recarga con el saldo disponible obtenido del servicio de Tarjetas
        Recarga recarga = new Recarga();
        recarga.setIdTarjeta(tarjeta.getIdTarjeta());
        recarga.setSaldoDisponible(tarjeta.getSaldoDisponible());
        recarga.setMontoRecarga(montoRecarga);
        recarga.setFechaRecarga(LocalDateTime.now());

        Recarga guardada = recargaRepository.save(recarga);

        // 3. Publicacion asincrona a la cola Apellido_Queue para el area de Riesgo
        recargaPublisher.publicarRecarga(guardada);

        return guardada;
    }
}
