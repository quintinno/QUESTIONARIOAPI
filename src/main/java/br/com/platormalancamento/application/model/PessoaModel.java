//package br.com.platormalancamento.application.model;
//
//import java.io.Serializable;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "TB_PESSOA")
//public class PessoaModel implements Serializable {
//
//	private static final long serialVersionUID = 1L;
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "CODIGO", nullable = false)
//	private Long codigo;
//	
//	@Column(name = "NOME_COMPLETO", nullable = false)
//	private String nomeCompleto;
//	
//	@Column(name = "EMAIL")
//	private String email;
//	
//	@Column(name = "TELEFONE")
//	private String telefone;
//	
//	@Column(name = "NUMERO_DOCUMENTO")
//	private String numeroDocumento;
//	
//	@Column(name = "FOTO", nullable = false)
//	private String foto;
//	
//	@Column(name = "IS_ATIVO", nullable = false)
//	private Boolean isAtivo;
//	
//	public PessoaModel() { }
//
//	public Long getCodigo() {
//		return codigo;
//	}
//
//	public void setCodigo(Long codigo) {
//		this.codigo = codigo;
//	}
//
//	public String getNomeCompleto() {
//		return nomeCompleto;
//	}
//
//	public void setNomeCompleto(String nomeCompleto) {
//		this.nomeCompleto = nomeCompleto;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getTelefone() {
//		return telefone;
//	}
//
//	public void setTelefone(String telefone) {
//		this.telefone = telefone;
//	}
//
//	public String getNumeroDocumento() {
//		return numeroDocumento;
//	}
//
//	public void setNumeroDocumento(String numeroDocumento) {
//		this.numeroDocumento = numeroDocumento;
//	}
//
//	public Boolean getIsAtivo() {
//		return isAtivo;
//	}
//
//	public void setIsAtivo(Boolean isAtivo) {
//		this.isAtivo = isAtivo;
//	}
//
//	public String getFoto() {
//		return foto;
//	}
//
//	public void setFoto(String foto) {
//		this.foto = foto;
//	}
//
//}
