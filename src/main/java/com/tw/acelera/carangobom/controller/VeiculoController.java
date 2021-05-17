package com.tw.acelera.carangobom.controller;

import com.tw.acelera.carangobom.controller.dto.VeiculoDto;
import com.tw.acelera.carangobom.controller.form.VeiculoForm;
import com.tw.acelera.carangobom.modelo.Veiculo;
import com.tw.acelera.carangobom.repository.VeiculoRepository;
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
@RequestMapping("/veiculos")
public class VeiculoController {
	
	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@RequestMapping("/listar")
	public List<VeiculoDto> lista() {
		List<Veiculo> veiculos = veiculoRepository.findAll();
		return VeiculoDto.converter(veiculos);
	}
	
	@PostMapping("/incluir")
	@Transactional
	public ResponseEntity<VeiculoDto> cadastrar(@RequestBody @Valid VeiculoForm form, UriComponentsBuilder uriBuilder) {
		Veiculo veiculos = form.converter();
		System.out.print(veiculos);
		veiculoRepository.save(veiculos);
		
		URI uri = uriBuilder.path("/veiculo/{id}").buildAndExpand(veiculos.getId()).toUri();
		return ResponseEntity.created(uri).body(new VeiculoDto(veiculos));
	}
	
	@DeleteMapping("/deletar/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Veiculo> optional = veiculoRepository.findById(id);
		if (optional.isPresent()) {
			veiculoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/editar/{id}")
	@Transactional
	public ResponseEntity<VeiculoDto> atualizar(@PathVariable Long id, @RequestBody @Valid VeiculoForm form) {
		Optional<Veiculo> optional = veiculoRepository.findById(id);
		if (optional.isPresent()) {
			Veiculo veiculo = form.atualizar(id, veiculoRepository);
			return ResponseEntity.ok(new VeiculoDto(veiculo));
		}
		
		return ResponseEntity.notFound().build();
	}

}
