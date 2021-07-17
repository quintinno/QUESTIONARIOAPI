package br.com.platormalancamento.application.model;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "TB_PERFIL")
public class PerfilModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CODIGO", nullable = false)
	private Long codigo;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "TB_USUARIO_PERFIL", joinColumns = 
		@JoinColumn(name = "ID_PERFIL", referencedColumnName = "codigo", nullable = false), inverseJoinColumns = 
		@JoinColumn(name = "ID_USUARIO", referencedColumnName = "codigo", nullable = false), uniqueConstraints = { 
				@UniqueConstraint(columnNames = { "ID_PERFIL", "ID_USUARIO" })})
	List<UsuarioModel> usuarioModelList = new ArrayList<UsuarioModel>();
	
	@Column(name = "NOME_PERFIL", nullable = false)
	private String nomePerfil;
	
	@Column(name = "DESCRICAO_PERFIL", nullable = false)
	private String descricaoPerfil;
	
	public PerfilModel() { }

	public PerfilModel(String nomePerfil, String descricaoPerfil) {
		this.nomePerfil = nomePerfil;
		this.descricaoPerfil = descricaoPerfil;
	}

	public PerfilModel(String nomePerfil) {
		this.nomePerfil = nomePerfil;	
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNomePerfil() {
		return nomePerfil;
	}

	public void setNomePerfil(String nomePerfil) {
		this.nomePerfil = nomePerfil;
	}

	public List<UsuarioModel> getUsuarioModelList() {
		return usuarioModelList;
	}

	public void setUsuarioModelList(List<UsuarioModel> usuarioModelList) {
		this.usuarioModelList = usuarioModelList;
	}

	public String getDescricaoPerfil() {
		return descricaoPerfil;
	}

	public void setDescricaoPerfil(String descricaoPerfil) {
		this.descricaoPerfil = descricaoPerfil;
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
		PerfilModel other = (PerfilModel) obj;
		return Objects.equals(codigo, other.codigo);
	}

}
