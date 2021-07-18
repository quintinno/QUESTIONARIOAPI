package br.com.platormalancamento.application.model;

import org.springframework.security.core.GrantedAuthority;

public class AuthorityModel implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
	
	private String authority;

	public AuthorityModel(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return this.authority;
	}

}
