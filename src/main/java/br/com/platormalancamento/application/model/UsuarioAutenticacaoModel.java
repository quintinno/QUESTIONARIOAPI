package br.com.platormalancamento.application.model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuarioAutenticacaoModel implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private Long codigo;
	
	private String identificador;
	
	private String senha;
	
	private Collection<? extends GrantedAuthority> grantedAuthoritieList;
	
	public UsuarioAutenticacaoModel() { }

	public UsuarioAutenticacaoModel(Long codigo, String identificador, String senha, List<PerfilModel> perfilModelList) {
		this.codigo = codigo;
		this.identificador = identificador;
		this.senha = senha;
		this.grantedAuthoritieList = perfilModelList.stream().map( perfil -> new SimpleGrantedAuthority(perfil.getDescricaoPerfil())).collect(Collectors.toList());
	}
	
	public boolean hasRole(PerfilModel perfilModel) {
		return getAuthorities().contains(new SimpleGrantedAuthority(perfilModel.getDescricaoPerfil()));
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
	
	@Override
	public String getUsername() {
		return identificador;
	}

	@Override
	public String getPassword() {
		return senha;
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

	public Long getCodigo() {
		return codigo;
	}

	public String getIdentificador() {
		return identificador;
	}

	public Collection<? extends GrantedAuthority> getGrantedAuthoritieList() {
		return grantedAuthoritieList;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public void setGrantedAuthoritieList(Collection<? extends GrantedAuthority> grantedAuthoritieList) {
		this.grantedAuthoritieList = grantedAuthoritieList;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
