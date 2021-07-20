package br.com.platormalancamento.application.service;

import br.com.platormalancamento.application.model.PerfilModel;
import br.com.platormalancamento.application.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class PerfilService implements Serializable, PerfilServiceInterface {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PerfilRepository perfilRepository;

	@Override
	public PerfilModel save(PerfilModel perfilModel) {
		return this.perfilRepository.save(perfilModel);
	}

	@Override
	public List<PerfilModel> findAll() {
		return this.perfilRepository.findAll();
	}

	@Override
	public PerfilModel findByNomePerfil(String nomePerfil) {
		return this.perfilRepository.findByNomePerfil(nomePerfil);
	}

}
