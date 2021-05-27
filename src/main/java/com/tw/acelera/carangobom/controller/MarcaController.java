package com.tw.acelera.carangobom.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.tw.acelera.carangobom.controller.dto.MarcaDto;
import com.tw.acelera.carangobom.controller.form.AtualizacaoMarcaForm;
import com.tw.acelera.carangobom.controller.form.MarcaForm;
import com.tw.acelera.carangobom.modelo.Marca;
import com.tw.acelera.carangobom.repository.MarcaRepository;


@RestController
@RequestMapping("/marcas")
public class MarcaController {
	
	@Autowired
	private MarcaRepository marcaRepository;
	
	@RequestMapping("/listar")
	public List<MarcaDto> lista() {
		List<Marca> marcas = marcaRepository.findAll();
		return MarcaDto.converter(marcas);		
	}
	
	@PostMapping("/incluir")
	@Transactional
	public ResponseEntity<MarcaDto> cadastrar(@RequestBody @Valid MarcaForm form, UriComponentsBuilder uriBuilder) {
		Marca marcas = form.converter();
		marcaRepository.save(marcas);
		
		URI uri = uriBuilder.path("/marcas/{id}").buildAndExpand(marcas.getId()).toUri();
		return ResponseEntity.created(uri).body(new MarcaDto(marcas));
	}
	
	@DeleteMapping("/deletar/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Marca> optional = marcaRepository.findById(id);
		if (optional.isPresent()) {
			marcaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/editar/{id}")
	@Transactional
	public ResponseEntity<MarcaDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoMarcaForm form) {
		Optional<Marca> optional = marcaRepository.findById(id);
		if (optional.isPresent()) {
			Marca marca = form.atualizar(id, marcaRepository);
			return ResponseEntity.ok(new MarcaDto(marca));
		}
		
		return ResponseEntity.notFound().build();
	}

}
