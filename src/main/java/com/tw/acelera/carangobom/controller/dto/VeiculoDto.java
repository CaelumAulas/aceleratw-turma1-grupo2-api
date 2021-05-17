package com.tw.acelera.carangobom.controller.dto;
import com.tw.acelera.carangobom.modelo.Marca;
import com.tw.acelera.carangobom.modelo.Veiculo;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class VeiculoDto {
	private Long id;
	
	private Marca marca;
	
	private BigDecimal valor;
	
	private String modelo;
	
	private int ano;

	public VeiculoDto(Veiculo veiculo) {
		this.id = veiculo.getId();
		this.modelo = veiculo.getModelo();
		this.ano = veiculo.getAno();
		this.marca = veiculo.getMarca();
		this.valor = veiculo.getValor();
	}



	public Marca getMarca() {
		return marca;
	}



	public void setMarca(Marca marca) {
		this.marca = marca;
	}



	public BigDecimal getValor() {
		return valor;
	}



	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}



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



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public static List<VeiculoDto> converter(List<Veiculo> usuarios) {
		return usuarios.stream().map(VeiculoDto::new).collect(Collectors.toList());
	}

}
