package br.com.platormalancamento.application.utility;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class AutenticacaoUtility implements Serializable {

	private static final long serialVersionUID = 1L;

	@Value("${jwt.secret}")
	private String autenticacaoSegredo;
	
	@Value("${jwt.expiration}")
	private Long autenticacaoTempoExpiracaoChave;
	
	// Gerador do TOKEN JWT
	public String gerarChaveAutenticacao(String identificador) {
		return Jwts
			   .builder()
			   .setSubject(identificador)
			   .setExpiration(new Date(System.currentTimeMillis() + autenticacaoTempoExpiracaoChave))
			   .signWith(SignatureAlgorithm.HS512, autenticacaoSegredo.getBytes())
			   .compact();
	}
	
}
