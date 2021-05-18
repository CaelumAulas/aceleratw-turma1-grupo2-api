package com.tw.acelera.carangobom.controller.form;

import com.tw.acelera.carangobom.modelo.Marca;
import com.tw.acelera.carangobom.modelo.Veiculo;
import com.tw.acelera.carangobom.repository.MarcaRepository;
import com.tw.acelera.carangobom.repository.VeiculoRepository;

import java.math.BigDecimal;

public class VeiculoForm {
	private String modelo;
	private int ano;
	private BigDecimal valor;
	private String marca;
//	private Long idMarca;
//	private Marca marca;

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Veiculo converter(MarcaRepository marcaRepository) {
		Marca mar = marcaRepository.findByDescricao(marca);
		return new Veiculo(mar, valor, modelo, ano);
	}
	
	
	public Veiculo atualizar(Long id, VeiculoRepository veiculoRepository) {
		Veiculo veiculo = veiculoRepository.getOne(id);

//		veiculo.setMarca(this.marca);
		veiculo.setValor(this.valor);
		veiculo.setModelo(this.modelo);
		veiculo.setAno(this.ano);

		return veiculo;
	}
	
}
