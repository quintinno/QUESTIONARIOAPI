package br.com.platormalancamento.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.platormalancamento.application.model.PerfilModel;

public interface PerfilRepository extends JpaRepository<PerfilModel, Long> { }
