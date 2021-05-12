package com.tw.acelera.carangobom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tw.acelera.carangobom.modelo.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
	
}
