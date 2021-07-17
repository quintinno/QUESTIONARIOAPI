package br.com.platormalancamento.application.dto;

import java.io.Serializable;

public class UsuarioDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String identificador;
	
	private String senha;
	
	public UsuarioDTO() { }

	public UsuarioDTO(String identificador, String senha) {
		this.identificador = identificador;
		this.senha = senha;
	}

	public String getIdentificador() {
		return identificador;
	}

	public String getSenha() {
		return senha;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
