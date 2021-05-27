package com.tw.acelera.carangobom.controller.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.tw.acelera.carangobom.modelo.Marca;
import com.tw.acelera.carangobom.repository.MarcaRepository;

public class MarcaControllerTest {

	  @Autowired
	  private TestEntityManager entityManager;

	  @Autowired
	  MarcaRepository repository;

	  @Test
	  public void verificaSeRepositoryVazio() {
	    Iterable<Marca> marcas = repository.findAll();

	    assertThat(marcas).isEmpty();
	  }

	  @Test
	  public void deveMostrarMarcaCadastrada() {
		  Marca marca = new Marca("Volks");
	      repository.save(marca);

	      assertEquals("Volks", marca.getDescricao());
	  }

	  @Test
	  public void pegaTodosMarcasAdicionadas() {
		  Marca marca = new Marca("Volks");
		  Marca marca1 = new Marca("Marca verde");
		  Marca marca2 = new Marca("Azul");
		  Marca marca3 = new Marca("Rosa");
	      entityManager.persist(marca);
	      entityManager.persist(marca1);
	      entityManager.persist(marca2);
	      entityManager.persist(marca3);

	      Iterable<Marca> marcas = repository.findAll();
	      assertThat(marcas).hasSize(4).contains(marca, marca1, marca2, marca3);
	  }
	  
	  @Test
	  public void verificaMarca() {
		  Marca marca = new Marca("BMW");
		  Marca marca1 = new Marca("ZURICH");
		  Marca marca2 = new Marca("AZUL");
		  Marca marca3 = new Marca("MAR");
	      entityManager.persist(marca);	
	      entityManager.persist(marca1);    
	      entityManager.persist(marca2);	      	
	      entityManager.persist(marca3);		      

	      Marca zu = repository.findByDescricao("ZURICH");
	      assertEquals("ZURICH", zu.getDescricao());
	      
	  }

}
