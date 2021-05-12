package com.tw.acelera.carangobom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tw.acelera.carangobom.modelo.Veiculo;


public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
	Veiculo findByModelo(String modelo);
}
