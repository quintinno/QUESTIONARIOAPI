package br.com.platormalancamento.application.dto;

public class JwtRequestDTO {
	
	public String username;
	
	public String password;
	
	public JwtRequestDTO() { }

	public JwtRequestDTO(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
