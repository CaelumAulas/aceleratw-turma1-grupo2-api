package com.tw.acelera.carangobom.controller.dto;

import java.util.List;
import java.util.stream.Collectors;
import com.tw.acelera.carangobom.modelo.Marca;

public class MarcaDto {
	private Long id;
	private String descricao;
	
	public MarcaDto(Marca marca) {
		this.id = marca.getId();
		this.descricao = marca.getDescricao();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static List<MarcaDto> converter(List<Marca> marcas) {
		return marcas.stream().map(MarcaDto::new).collect(Collectors.toList());
	}

}
