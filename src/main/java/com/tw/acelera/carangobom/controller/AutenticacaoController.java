package com.tw.acelera.carangobom.controller;

import javax.validation.Valid;

import com.tw.acelera.carangobom.controller.dto.JwtAuthenticationResponse;
import com.tw.acelera.carangobom.controller.dto.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import com.tw.acelera.carangobom.config.security.TokenService;

import com.tw.acelera.carangobom.controller.dto.TokenDto;
import com.tw.acelera.carangobom.controller.form.LoginForm;

@RestController
@RequestMapping("/auth")
@Profile("prod")
@CrossOrigin("http://localhost:3000")
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<?> autenticar(@RequestBody @Valid LoginForm form) {
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();
		
		try {
			Authentication authentication = authManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authentication);
			LoginResponse response = new LoginResponse();
			response.jwtAuthenticationResponse = new JwtAuthenticationResponse(token);
//			response.user = email.get();
			return ResponseEntity.ok(response);
//			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
}

