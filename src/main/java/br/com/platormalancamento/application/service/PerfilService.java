package br.com.platormalancamento.application.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.platormalancamento.application.model.PerfilModel;
import br.com.platormalancamento.application.repository.PerfilRepository;

@Service
public class PerfilService implements Serializable, PerfilServiceInterface {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PerfilRepository perfilRepository;

	@Override
	public PerfilModel save(PerfilModel perfilModel) {
		return this.perfilRepository.save(perfilModel);
	}
	
}
