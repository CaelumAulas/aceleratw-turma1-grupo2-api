package com.tw.acelera.carangobom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tw.acelera.carangobom.controller.dto.MarcaDto;
import com.tw.acelera.carangobom.modelo.Marca;
import com.tw.acelera.carangobom.repository.MarcaRepository;


@RestController
@RequestMapping("/marcas")
public class MarcaController {
	
	@Autowired
	private MarcaRepository marcaRepository;
	
	@GetMapping
	public List<MarcaDto> lista() {
		List<Marca> marcas = marcaRepository.findAll();
		return MarcaDto.converter(marcas);		
	}

}
