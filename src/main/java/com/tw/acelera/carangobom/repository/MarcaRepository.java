package com.tw.acelera.carangobom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tw.acelera.carangobom.modelo.Marca;

import java.util.Optional;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
    Optional<Marca> findById(Long id);

    Marca findByDescricao(String desc);
}
