package br.com.platormalancamento.application.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class ValidadorFilterSecurity extends BasicAuthenticationFilter {
	
	private static final String HEADER_ATRIBUTO = "Authorization";
	private static final String HEADER_PREFIXO = "Bearer ";

	public ValidadorFilterSecurity(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws IOException, ServletException {
		String prefixoHeader = httpServletRequest.getHeader(HEADER_ATRIBUTO);
		if(prefixoHeader == null) {
			filterChain.doFilter(httpServletRequest, httpServletResponse);
			return;
		}
		if(!prefixoHeader.startsWith(HEADER_PREFIXO)) {
			filterChain.doFilter(httpServletRequest, httpServletResponse);
			return;
		}
		
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = this.recuperarAutenticadorTOKEN(prefixoHeader.replace(HEADER_PREFIXO, ""));
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}
	
	private UsernamePasswordAuthenticationToken recuperarAutenticadorTOKEN(String TOKEN) {
		String identificador = JWT.require(Algorithm.HMAC512(AutenticadorFilterSecurity.getChaveToken())).build().verify(TOKEN).getSubject();
		if(identificador == null) {
			return null;
		}
		return new UsernamePasswordAuthenticationToken(identificador, null, new ArrayList<>()); 
	}

}
