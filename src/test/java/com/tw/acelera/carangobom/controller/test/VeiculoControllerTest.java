package com.tw.acelera.carangobom.controller.test;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.tw.acelera.carangobom.modelo.Marca;
import com.tw.acelera.carangobom.modelo.Veiculo;
import com.tw.acelera.carangobom.repository.VeiculoRepository;

import org.junit.Test;

public class VeiculoControllerTest {

	 @Autowired
	  private TestEntityManager entityManager;

	  @Autowired
	  VeiculoRepository repository;

	  @Test
	  public void verificaSeRepositoryVazio() {
	    Iterable<Veiculo> veiculos = repository.findAll();

	    assertThat(veiculos).isEmpty();
	  }
	  
	  @Test
	  public void deveMostrarVeiculoCadastrado() {
		  Marca marc = new Marca("Volks");
		  Veiculo vei = new Veiculo(marc, new BigDecimal(55000.00), "modelo X", 2021);
	      repository.save(vei);

	      assertEquals("Volks", vei.getMarca().getDescricao());
	      assertEquals(new BigDecimal("55000.00"), vei.getValor());
	      assertEquals("modelo X", vei.getModelo());
	      assertEquals(2021, vei.getAno());
	  }

	  @Test
	  public void pegaTodosVeiculosAdicionados() {
		  Marca marc = new Marca("Volks");
		  entityManager.persist(marc);
		  Veiculo vei = new Veiculo(marc, new BigDecimal(55000.00), "modelo X", 2021);	
	      entityManager.persist(vei);
  
	      Veiculo vei2 = new Veiculo(marc, new BigDecimal(35000.00), "modelo A", 2021);	
	      entityManager.persist(vei2);	

	      Iterable<Veiculo> todos = repository.findAll();
	      assertThat(todos).hasSize(2).contains(vei, vei2);
	  }
	  
	  @Test
	  public void verificaTodosVeiculosPelaMarca() {
		  Marca marc = new Marca("BMW");
		  Veiculo vei = new Veiculo(marc, new BigDecimal(55000.00), "modelo X", 2021);	
	      entityManager.persist(vei);
	      Veiculo veiB = new Veiculo(marc, new BigDecimal(335000.00), "modelo X", 2021);	
	      entityManager.persist(veiB);
	      
	      Marca marc2 = new Marca("ZURICH");
  
	      Veiculo vei2 = new Veiculo(marc2, new BigDecimal(35000.00), "modelo A", 2021);	
	      entityManager.persist(vei2);	
	      
	      Veiculo vei3 = new Veiculo(marc2, new BigDecimal(15000.00), "modelo A", 2021);	
	      entityManager.persist(vei3);
	      
	      Veiculo vei4 = new Veiculo(marc2, new BigDecimal(25000.00), "modelo A", 2021);	
	      entityManager.persist(vei4);		      

	      Iterable<Veiculo> todosZurich = repository.findByMarcaDescricao("ZURICH", null);
	      assertThat(todosZurich).hasSize(2).contains(vei2, vei3, vei4);
	      
	      Iterable<Veiculo> todosBMW = repository.findByMarcaDescricao("BMW", null);
	      assertThat(todosBMW).hasSize(2).contains(vei, veiB);
	  }


}
