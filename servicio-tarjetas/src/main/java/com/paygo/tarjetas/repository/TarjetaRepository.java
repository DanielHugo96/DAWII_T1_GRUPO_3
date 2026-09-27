package com.paygo.tarjetas.repository;

import com.paygo.tarjetas.entity.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarjetaRepository extends JpaRepository<Tarjeta, String> {
}
