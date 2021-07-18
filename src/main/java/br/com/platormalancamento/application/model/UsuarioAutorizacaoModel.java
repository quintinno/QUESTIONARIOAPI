package br.com.platormalancamento.application.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuarioAutorizacaoModel implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private final Optional<UsuarioModel> usuarioModelOptional;

	public UsuarioAutorizacaoModel(Optional<UsuarioModel> usuarioModelOptional) {
		this.usuarioModelOptional = usuarioModelOptional;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<>();
	}
	
	@Override
	public String getUsername() {
		return usuarioModelOptional.orElse(new UsuarioModel()).getIdentificador();
	}

	@Override
	public String getPassword() {
		return usuarioModelOptional.orElse(new UsuarioModel()).getSenha();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Optional<UsuarioModel> getUsuarioModelOptional() {
		return usuarioModelOptional;
	}

}
