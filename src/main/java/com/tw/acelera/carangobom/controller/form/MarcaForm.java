package com.tw.acelera.carangobom.controller.form;

import javax.validation.constraints.NotEmpty;


import com.sun.istack.NotNull;
import com.tw.acelera.carangobom.modelo.Marca;

public class MarcaForm {
	
	@NotNull @NotEmpty
	private String descricao;

	
	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Marca converter() {
		return new Marca(descricao);
	}
}
