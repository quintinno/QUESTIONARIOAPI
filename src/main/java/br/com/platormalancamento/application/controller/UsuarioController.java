package br.com.platormalancamento.application.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.platormalancamento.application.model.UsuarioModel;
import br.com.platormalancamento.application.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping
	public ResponseEntity<UsuarioModel> save(@RequestBody UsuarioModel usuarioModel) {
		return ResponseEntity.ok(this.usuarioService.save(usuarioModel));
	}
	
	@GetMapping
	public ResponseEntity<List<UsuarioModel>> findAll() {
		List<UsuarioModel> usuarioModelList = this.usuarioService.findAll();
		return !usuarioModelList.isEmpty() ? ResponseEntity.status(HttpStatus.OK).body(usuarioModelList) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
