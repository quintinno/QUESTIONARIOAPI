package br.com.platormalancamento.application.repository;

import br.com.platormalancamento.application.model.PerfilModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<PerfilModel, Long> {
    public PerfilModel findByNomePerfil(String nomePerfil);
}
