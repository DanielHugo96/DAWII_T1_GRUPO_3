package com.paygo.tarjetas.service;

import com.paygo.tarjetas.entity.Tarjeta;
import com.paygo.tarjetas.repository.TarjetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TarjetaService {

    private final TarjetaRepository tarjetaRepository;

    public List<Tarjeta> listarTarjetas() {
        return tarjetaRepository.findAll();
    }

    public Optional<Tarjeta> buscarPorId(String idTarjeta) {
        return tarjetaRepository.findById(idTarjeta);
    }

    public Tarjeta registrarTarjeta(Tarjeta tarjeta) {
        return tarjetaRepository.save(tarjeta);
    }
}
