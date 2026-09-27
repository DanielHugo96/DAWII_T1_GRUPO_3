package com.paygo.tarjetas.controller;

import com.paygo.tarjetas.entity.Tarjeta;
import com.paygo.tarjetas.service.TarjetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarjetas")
@RequiredArgsConstructor
public class TarjetaController {

    private final TarjetaService tarjetaService;

    // 1. Consultar el listado de tarjetas
    @GetMapping
    public ResponseEntity<List<Tarjeta>> listar() {
        return ResponseEntity.ok(tarjetaService.listarTarjetas());
    }

    // 2. Consultar una tarjeta por su id
    @GetMapping("/{idTarjeta}")
    public ResponseEntity<Tarjeta> buscarPorId(@PathVariable String idTarjeta) {
        return tarjetaService.buscarPorId(idTarjeta)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint auxiliar para registrar tarjetas de prueba (no exigido por el enunciado)
    @PostMapping
    public ResponseEntity<Tarjeta> registrar(@RequestBody Tarjeta tarjeta) {
        Tarjeta guardada = tarjetaService.registrarTarjeta(tarjeta);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardada);
    }
}
