package br.com.platormalancamento.application.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "TB_USUARIO")
public class UsuarioModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CODIGO", nullable = false)
	private Long codigo;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "TB_USUARIO_PERFIL", joinColumns = 
		@JoinColumn(name = "ID_USUARIO", referencedColumnName = "codigo", nullable = false), inverseJoinColumns = 
		@JoinColumn(name = "ID_PERFIL", referencedColumnName = "codigo", nullable = false), uniqueConstraints = { 
				@UniqueConstraint(columnNames = { "ID_USUARIO", "ID_PERFIL" })})
	List<PerfilModel> perfilModelList = new ArrayList<PerfilModel>();

	@Column(name = "IDENTIFICADOR", unique = true, nullable = false)
	private String identificador;
	
	@JsonIgnore
	@Column(name = "SENHA", nullable = false)
	private String senha;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_CRIACAO_ATUALIZACAO", nullable = false)
	private java.util.Date dataCricaoAtualizacao;
	
	@Column(name = "USUARIO_CRIACAO_ATUALIZACAO", nullable = false)
	private String usuarioCriacaoAtualizacao;
	
	@Column(name = "IS_ATIVO", nullable = false)
	private Boolean isAtivo;
	
	public UsuarioModel() { }

	public UsuarioModel(String identificador, String senha, Date dataCricaoAtualizacao, String usuarioCriacaoAtualizacao, Boolean isAtivo) {
		this.identificador = identificador;
		this.senha = senha;
		this.dataCricaoAtualizacao = dataCricaoAtualizacao;
		this.usuarioCriacaoAtualizacao = usuarioCriacaoAtualizacao;
		this.isAtivo = isAtivo;
	}

	public Long getCodigo() {
		return codigo;
	}

	public List<PerfilModel> getPerfilModelList() {
		return perfilModelList;
	}

	public String getIdentificador() {
		return identificador;
	}

	public String getSenha() {
		return senha;
	}

	public java.util.Date getDataCricaoAtualizacao() {
		return dataCricaoAtualizacao;
	}

	public String getUsuarioCriacaoAtualizacao() {
		return usuarioCriacaoAtualizacao;
	}

	public Boolean getIsAtivo() {
		return isAtivo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public void setPerfilModelList(List<PerfilModel> perfilModelList) {
		this.perfilModelList = perfilModelList;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setDataCricaoAtualizacao(java.util.Date dataCricaoAtualizacao) {
		this.dataCricaoAtualizacao = dataCricaoAtualizacao;
	}

	public void setUsuarioCriacaoAtualizacao(String usuarioCriacaoAtualizacao) {
		this.usuarioCriacaoAtualizacao = usuarioCriacaoAtualizacao;
	}

	public void setIsAtivo(Boolean isAtivo) {
		this.isAtivo = isAtivo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioModel other = (UsuarioModel) obj;
		return Objects.equals(codigo, other.codigo);
	}
	
}
