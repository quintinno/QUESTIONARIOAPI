package br.com.platormalancamento.application.service;

import br.com.platormalancamento.application.model.PerfilModel;

import java.util.List;

public interface PerfilServiceInterface {
	public PerfilModel save(PerfilModel perfilModel);
	public List<PerfilModel> findAll();
	public PerfilModel findByNomePerfil(String nomePerfil);
}
