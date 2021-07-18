package br.com.platormalancamento.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.platormalancamento.application.dto.JwtRequestDTO;
import br.com.platormalancamento.application.dto.JwtResponseDTO;
import br.com.platormalancamento.application.service.UserDetailsService;
import br.com.platormalancamento.application.utility.JwtUtility;

@RestController
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtility jwtUtility;
	
	@PostMapping("/autenticador")
	public ResponseEntity<?> autenticador(@RequestBody JwtRequestDTO jwtRequestDTO) throws Exception {
		try {
			authenticate(jwtRequestDTO.getUsername(), jwtRequestDTO.getPassword());
		} catch (UsernameNotFoundException usernameNotFoundException) {
			throw new Exception("ERROR: USUARIO NAO ENCONTRADO " + usernameNotFoundException.getMessage());
		}
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequestDTO.getUsername());
		String TOKEN = this.jwtUtility.generateToken(userDetails);
		
		return ResponseEntity.ok(new JwtResponseDTO(TOKEN));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException disabledException) {
			throw new Exception("ERROR: USUARIO NAO HABILITADO " + disabledException.getMessage());
		} catch (BadCredentialsException badCredentialsException) {
			throw new Exception("ERROR: CREDENCIAIS INVALIDAS " + badCredentialsException.getMessage());
		}
	}
	
}
