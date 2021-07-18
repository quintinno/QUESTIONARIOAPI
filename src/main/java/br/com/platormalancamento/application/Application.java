package br.com.platormalancamento.application;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.platormalancamento.application.model.PerfilModel;
import br.com.platormalancamento.application.model.UsuarioModel;
import br.com.platormalancamento.application.service.PerfilService;
import br.com.platormalancamento.application.service.UsuarioService;

@SpringBootApplication
public class Application implements CommandLineRunner {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PerfilService perfilService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::: QUESTIONARIOAPI :::::::::::::::::::::::::::::::::::::::::::::::::");
		this.cadastrarUsuario();
	}
	
	public void cadastrarUsuario() {
		PerfilModel perfilModel1 = new PerfilModel();
		PerfilModel perfilModel2 = new PerfilModel();
		PerfilModel perfilModel3 = new PerfilModel();
			perfilModel1.setNomePerfil("ADMINISTRADOR");
			perfilModel2.setNomePerfil("PUBLICO_ALVO");
			perfilModel3.setNomePerfil("APLICADOR");
			this.perfilService.save(perfilModel1);
			this.perfilService.save(perfilModel2);
			this.perfilService.save(perfilModel3);
		UsuarioModel usuarioModel1 = new UsuarioModel();
			usuarioModel1.setIdentificador("desenvolvimento");
			usuarioModel1.setChave("desenvolvimento");
			usuarioModel1.setDataCricaoAtualizacao(new Date());
			usuarioModel1.setUsuarioCriacaoAtualizacao("QUESTIONARIO_CARGA_BASICA");
			usuarioModel1.setIsAtivo(true);
			usuarioModel1.getPerfilModelList().add(perfilModel1);
			usuarioModel1.getPerfilModelList().add(perfilModel2);
			usuarioModel1.getPerfilModelList().add(perfilModel3);
			this.usuarioService.save(usuarioModel1);
	}

}
