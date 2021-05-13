package com.tw.acelera.carangobom.controller.form;

import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;
import com.tw.acelera.carangobom.modelo.Marca;
import com.tw.acelera.carangobom.repository.MarcaRepository;

public class AtualizacaoMarcaForm {
	
	@NotNull @NotEmpty
	private String descricao;

	
	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Marca atualizar(Long id, MarcaRepository marcaRepository) {
		Marca marca = marcaRepository.getOne(id);
		marca.setDescricao(this.descricao);
		
		return marca;
	}
}
