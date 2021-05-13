package com.tw.acelera.carangobom.repository;

import com.tw.acelera.carangobom.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
}
