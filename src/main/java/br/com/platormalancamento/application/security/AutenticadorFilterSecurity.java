package br.com.platormalancamento.application.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.id.GUIDGenerator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.platormalancamento.application.model.UsuarioAutorizacaoModel;

public class AutenticadorFilterSecurity extends UsernamePasswordAuthenticationFilter {
	
	private final AuthenticationManager authenticationManager;
	
	private static final Integer TEMPO_VALIDADE_TOKEN = 600_000;
	
	private static final String CHAVE_TOKEN = GUIDGenerator.GENERATOR_NAME;
	
	public AutenticadorFilterSecurity(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException {
		try {
			UsuarioAutorizacaoModel usuarioAutorizacaoModel = new ObjectMapper().readValue(httpServletRequest.getInputStream(), UsuarioAutorizacaoModel.class);
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuarioAutorizacaoModel.getUsername(), usuarioAutorizacaoModel.getPassword()));
		} catch (IOException e) {
			throw new RuntimeException("FALHA AO AUTENTICAR O USUARIO!");
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain, Authentication authentication) throws IOException, ServletException {
		UsuarioAutorizacaoModel usuarioAutorizacaoModel = (UsuarioAutorizacaoModel) authentication.getPrincipal();
		String TOKEN = JWT.create().withSubject(usuarioAutorizacaoModel.getUsername()).withExpiresAt(new Date(System.currentTimeMillis() + TEMPO_VALIDADE_TOKEN)).sign(Algorithm.HMAC512(CHAVE_TOKEN));
		httpServletResponse.getWriter().write(TOKEN);
		httpServletResponse.getWriter().flush();
	}

	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	public static Integer getTempoValidadeToken() {
		return TEMPO_VALIDADE_TOKEN;
	}

	public static String getChaveToken() {
		return CHAVE_TOKEN;
	}

}
