package com.tw.acelera.carangobom.controller.form;

import com.sun.istack.NotNull;
import com.tw.acelera.carangobom.modelo.Marca;
import com.tw.acelera.carangobom.modelo.Veiculo;
import com.tw.acelera.carangobom.repository.VeiculoRepository;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;

public class VeiculoForm {
	
	@NotNull @NotEmpty
	private BigDecimal valor;
	
	private String modelo;
	
	private int ano;
	
	private Marca marca;

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}
	
	public Veiculo converter() {
		return new Veiculo(marca, valor, modelo, ano);
	}
	
	public Veiculo atualizar(Long id, VeiculoRepository veiculoRepository) {
		Veiculo veiculo = veiculoRepository.getOne(id);
		veiculo.setMarca(this.marca);
		veiculo.setValor(this.valor);
		veiculo.setModelo(this.modelo);
		veiculo.setAno(this.ano);

		return veiculo;
	}
	
}
