package br.com.platormalancamento.application.dto;

public class JwtResponseDTO {
	
	public String TOKEN;
	
	public JwtResponseDTO() { }

	public JwtResponseDTO(String tOKEN) {
		TOKEN = tOKEN;
	}

	public String getTOKEN() {
		return TOKEN;
	}

	public void setTOKEN(String tOKEN) {
		TOKEN = tOKEN;
	}

}
