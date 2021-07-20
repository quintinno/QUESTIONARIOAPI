package br.com.platormalancamento.application.controller;

import br.com.platormalancamento.application.model.UsuarioModel;
import br.com.platormalancamento.application.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/registrar-usuario")
	public ResponseEntity<UsuarioModel> save(@RequestBody UsuarioModel usuarioModel) {
		return ResponseEntity.ok(this.usuarioService.save(usuarioModel));
	}
	
	@GetMapping
	public ResponseEntity<List<UsuarioModel>> findAll() {
		List<UsuarioModel> usuarioModelList = this.usuarioService.findAll();
		return !usuarioModelList.isEmpty() ? ResponseEntity.status(HttpStatus.OK).body(usuarioModelList) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PostMapping("/recuperar-usuario")
	public ResponseEntity<UsuarioModel> recuperarUsuario(@RequestBody UsuarioModel usuarioModel) {
		return ResponseEntity.ok().body(this.usuarioService.recuperarUsuario(usuarioModel));
	}

}
