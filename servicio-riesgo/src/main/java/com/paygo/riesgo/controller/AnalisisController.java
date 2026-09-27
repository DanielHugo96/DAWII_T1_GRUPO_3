package com.paygo.riesgo.controller;

import com.paygo.riesgo.entity.Analisis;
import com.paygo.riesgo.repository.AnalisisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/analisis")
@RequiredArgsConstructor
public class AnalisisController {

    private final AnalisisRepository analisisRepository;

    @GetMapping
    public ResponseEntity<List<Analisis>> listar() {
        return ResponseEntity.ok(analisisRepository.findAll());
    }
}
