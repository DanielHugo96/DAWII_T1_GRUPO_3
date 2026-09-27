package com.paygo.recargas.controller;

import com.paygo.recargas.dto.RecargaRequest;
import com.paygo.recargas.entity.Recarga;
import com.paygo.recargas.repository.RecargaRepository;
import com.paygo.recargas.service.RecargaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recargas")
@RequiredArgsConstructor
public class RecargaController {

    private final RecargaService recargaService;
    private final RecargaRepository recargaRepository;

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody RecargaRequest request) {
        try {
            Recarga recarga = recargaService.registrarRecarga(request.getIdTarjeta(), request.getMontoRecarga());
            return ResponseEntity.status(HttpStatus.CREATED).body(recarga);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<Recarga>> listar() {
        return ResponseEntity.ok(recargaRepository.findAll());
    }
}
