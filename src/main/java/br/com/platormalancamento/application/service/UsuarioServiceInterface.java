package br.com.platormalancamento.application.service;

import java.util.List;

import br.com.platormalancamento.application.model.UsuarioModel;

public interface UsuarioServiceInterface {
	public UsuarioModel save(UsuarioModel usuarioModel);
	public List<UsuarioModel> findAll();
}
