package br.com.platormalancamento.application.controller;

import br.com.platormalancamento.application.dto.JwtRequestDTO;
import br.com.platormalancamento.application.dto.JwtResponseDTO;
import br.com.platormalancamento.application.model.UsuarioModel;
import br.com.platormalancamento.application.service.UserDetailsService;
import br.com.platormalancamento.application.utility.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtility jwtUtility;
	
	@PostMapping("/autenticador")
	public ResponseEntity<JwtResponseDTO> autenticador(@RequestBody JwtRequestDTO jwtRequestDTO) throws Exception {
		try {
			authenticate(jwtRequestDTO.getUsername(), jwtRequestDTO.getPassword());
		} catch (UsernameNotFoundException usernameNotFoundException) {
			throw new Exception("ERROR: USUARIO NAO ENCONTRADO " + usernameNotFoundException.getMessage());
		}
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequestDTO.getUsername());
		return ResponseEntity.ok(new JwtResponseDTO(this.jwtUtility.generateToken(userDetails)));
	}

	@PostMapping("/gerar-token")
	public ResponseEntity<String> gerarToken(@RequestBody JwtRequestDTO jwtRequestDTO) throws Exception {
		try {
			authenticate(jwtRequestDTO.getUsername(), jwtRequestDTO.getPassword());
		} catch (UsernameNotFoundException usernameNotFoundException) {
			throw new Exception("ERROR: USUARIO NAO ENCONTRADO " + usernameNotFoundException.getMessage());
		}
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequestDTO.getUsername());
		return ResponseEntity.ok(this.jwtUtility.generateToken(userDetails));
	}

	@GetMapping("usuario-corrente")
	public UsuarioModel recuperarUsuarioConrrente(Principal principal) throws Exception {
		return (UsuarioModel) this.userDetailsService.loadUserByUsername(principal.getName());
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
