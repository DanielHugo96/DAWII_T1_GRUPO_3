package com.paygo.recargas.client;

import com.paygo.recargas.dto.TarjetaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Comunicacion sincronica con el servicio de Tarjetas usando OpenFeign.
// "url" apunta directo al servicio (sin Eureka). Si se registra en Eureka,
// se puede reemplazar "url" por "name" y usar el nombre de la app.
@FeignClient(name = "servicio-tarjetas", url = "${tarjetas.service.url}")
public interface TarjetaClient {

    @GetMapping("/api/tarjetas/{idTarjeta}")
    TarjetaDTO buscarPorId(@PathVariable("idTarjeta") String idTarjeta);
}
