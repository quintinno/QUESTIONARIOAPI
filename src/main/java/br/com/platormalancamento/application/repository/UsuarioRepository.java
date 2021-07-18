package br.com.platormalancamento.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import br.com.platormalancamento.application.model.UsuarioModel;

@Repository
@CrossOrigin(origins = "http://localhost:8080")
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {
	UsuarioModel findByIdentificador(String identificador);
}
