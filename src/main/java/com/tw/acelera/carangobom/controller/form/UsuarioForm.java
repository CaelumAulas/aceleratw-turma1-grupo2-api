package com.tw.acelera.carangobom.controller.form;

import com.sun.istack.NotNull;
import com.tw.acelera.carangobom.modelo.Marca;
import com.tw.acelera.carangobom.modelo.Usuario;
import com.tw.acelera.carangobom.repository.MarcaRepository;
import com.tw.acelera.carangobom.repository.UsuarioRepository;

import javax.validation.constraints.NotEmpty;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class UsuarioForm {
	
	@NotNull @NotEmpty
	private String email;
	private String senha;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario converter() {
		return new Usuario(email, senha);
	}

	public Usuario atualizar(Long id, UsuarioRepository usuarioRepository) {
		Usuario usuario = usuarioRepository.getOne(id);
		usuario.setEmail(this.email);
		usuario.setSenha(this.senha);

		return usuario;
	}
}
