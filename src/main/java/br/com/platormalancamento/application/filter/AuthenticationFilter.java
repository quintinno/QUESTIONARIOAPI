package br.com.platormalancamento.application.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.platormalancamento.application.dto.UsuarioDTO;
import br.com.platormalancamento.application.utility.AutenticacaoUtility;

@Configuration
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
    
    private AutenticacaoUtility autenticacaoUtility;

    public AuthenticationFilter(AuthenticationManager authenticationManager, AutenticacaoUtility autenticacaoUtility) {
    	setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
        this.authenticationManager = authenticationManager;
        this.autenticacaoUtility = autenticacaoUtility;
    }
	
	@Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException {
		try {
			UsuarioDTO usuarioDTO = new ObjectMapper().readValue(httpServletRequest.getInputStream(), UsuarioDTO.class);
	        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(usuarioDTO.getIdentificador(), usuarioDTO.getSenha(), new ArrayList<>());
	        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
	        return authentication;
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
    protected void successfulAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain, Authentication authentication) throws IOException, ServletException {
		UsuarioDTO usuarioDTO = new ObjectMapper().readValue(httpServletRequest.getInputStream(), UsuarioDTO.class);
		String identificador = (String) authentication.getPrincipal();
        String token = autenticacaoUtility.gerarChaveAutenticacao(usuarioDTO.getIdentificador());
        httpServletResponse.addHeader("Authorization", "Bearer " + token);
        httpServletResponse.addHeader("access-control-expose-headers", "Authorization");
	}
	
	private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {
        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
            response.setStatus(401);
            response.setContentType("application/json"); 
            response.getWriter().append(json());
        }
        
        private String json() {
            return "{\"timestamp\": " + new Date().getTime() + ", "
                + "\"status\": 401, "
                + "\"error\": \"Não autorizado\", "
                + "\"message\": \"Email ou senha inválidos\", "
                + "\"path\": \"/login\"}";
        }
    }

}
