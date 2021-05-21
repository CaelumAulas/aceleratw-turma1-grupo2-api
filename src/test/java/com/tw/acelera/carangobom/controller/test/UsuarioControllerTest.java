package com.tw.acelera.carangobom.controller.test;

import com.tw.acelera.carangobom.modelo.Usuario;
import com.tw.acelera.carangobom.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsuarioControllerTest {

	  @Autowired
	  private TestEntityManager entityManager;

	  @Autowired
	  UsuarioRepository repository;

	  @Test
	  public void verificaSeRepositoryVazio() {
	    Iterable<Usuario> usuarios = repository.findAll();
	    assertThat(usuarios).isEmpty();
	  }

	  @Test
	  public void devePegarEmailUsuarioCadastrado() {
		  Usuario user = new Usuario("usuario1@gmail.com", "12345");
	      repository.save(user);

	      assertEquals("usuario1@gmail.com", user.getEmail());
	  }

	  @Test
	  public void verificaTodosUsuariosAdicionados() {
		  Usuario user = new Usuario("usuario1@gmail.com", "12345");	
	      entityManager.persist(user);
 
	      Usuario user2 = new Usuario("usuario2@gmail.com", "12345");
	      entityManager.persist(user2);	

	      Iterable<Usuario> todos = repository.findAll();
	      assertThat(todos).hasSize(2).contains(user, user2);
	      
	      assertEquals("usuario1@gmail.com", user.getEmail());
	      assertEquals("usuario2@gmail.com", user2.getEmail());
	  }
	  
	  @Test
	  public void verificaUsuarioPeloEmail() {
		  Usuario user = new Usuario("usuario1@gmail.com", "12345");
	      entityManager.persist(user);	      
 
	      Usuario user2 = new Usuario("usuario2@gmail.com", "12345");
	      entityManager.persist(user2);	
	      
	      Usuario user3 = new Usuario("usuario3@gmail.com", "12345");	
	      entityManager.persist(user3);
	      
	      Usuario user4 = new Usuario("usuario4@gmail.com", "12345");	
	      entityManager.persist(user4);		      

	      Optional<Usuario> op = repository.findByEmail("usuario3@gmail.com");
	      assertEquals(user3, op.get());
	  }


}
