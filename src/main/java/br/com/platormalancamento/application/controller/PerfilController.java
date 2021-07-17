package br.com.platormalancamento.application.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.platormalancamento.application.model.PerfilModel;
import br.com.platormalancamento.application.repository.PerfilRepository;

@RestController
@RequestMapping("/perfil")
public class PerfilController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	@GetMapping
	public ResponseEntity<List<PerfilModel>> findAll() {
		return ResponseEntity.ok().body(this.perfilRepository.findAll());
	}

}
