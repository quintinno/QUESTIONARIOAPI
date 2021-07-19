package br.com.platormalancamento.application.dto;

public class JwtResponseDTO {
	
	public String token;
	
	public JwtResponseDTO() { }

	public JwtResponseDTO(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
