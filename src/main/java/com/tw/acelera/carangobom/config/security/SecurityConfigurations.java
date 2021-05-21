package com.tw.acelera.carangobom.config.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfiguration;
import java.util.ArrayList;
import java.util.List;

import com.tw.acelera.carangobom.repository.UsuarioRepository;


@EnableWebSecurity
@Configuration
@Profile("prod")
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	//Configuracoes de autenticacao
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	//Configuracoes de autorizacao
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	http.cors().configurationSource(request -> {
		  CorsConfiguration corsConfiguration = new CorsConfiguration();
		  corsConfiguration.setAllowedOrigins(List.of("*"));
		  corsConfiguration.setAllowedMethods(List.of("*"));
			  corsConfiguration.setAllowedHeaders(List.of("*"));
		      return corsConfiguration;
		});
		
		http
		.cors().and().csrf().disable()
		.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/auth").permitAll()
		.antMatchers(HttpMethod.GET, "/usuarios/**").permitAll()
		.antMatchers(HttpMethod.POST, "/usuarios/incluir").permitAll()
		.antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
		.antMatchers(HttpMethod.DELETE, "/usuarios/*").hasRole("MODERADOR")
		.anyRequest().authenticated()
		.and().csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);
	}
	
	
	//Configuracoes de recursos estaticos(js, css, imagens, etc.)
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
	}
	
}
