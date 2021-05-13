package com.tw.acelera.carangobom.controller;

import com.tw.acelera.carangobom.controller.dto.MarcaDto;
import com.tw.acelera.carangobom.controller.dto.UsuarioDto;
import com.tw.acelera.carangobom.controller.form.AtualizacaoMarcaForm;
import com.tw.acelera.carangobom.controller.form.MarcaForm;
import com.tw.acelera.carangobom.controller.form.UsuarioForm;
import com.tw.acelera.carangobom.modelo.Marca;
import com.tw.acelera.carangobom.modelo.Usuario;
import com.tw.acelera.carangobom.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@RequestMapping("/listar")
	public List<UsuarioDto> lista() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		return UsuarioDto.converter(usuarios);
	}
	
	@PostMapping("/incluir")
	@Transactional
	public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder uriBuilder) {
		Usuario usuarios = form.converter();
		usuarioRepository.save(usuarios);
		
		URI uri = uriBuilder.path("/marcas/{id}").buildAndExpand(usuarios.getId()).toUri();
		return ResponseEntity.created(uri).body(new UsuarioDto(usuarios));
	}
	
	@DeleteMapping("/deletar/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Usuario> optional = usuarioRepository.findById(id);
		if (optional.isPresent()) {
			usuarioRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/editar/{id}")
	@Transactional
	public ResponseEntity<UsuarioDto> atualizar(@PathVariable Long id, @RequestBody @Valid UsuarioForm form) {
		Optional<Usuario> optional = usuarioRepository.findById(id);
		if (optional.isPresent()) {
			Usuario usuario = form.atualizar(id, usuarioRepository);
			return ResponseEntity.ok(new UsuarioDto(usuario));
		}
		
		return ResponseEntity.notFound().build();
	}

}
