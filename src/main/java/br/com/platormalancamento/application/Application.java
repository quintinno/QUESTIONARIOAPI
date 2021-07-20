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
			usuarioModel1.setIdentificador("administrador");
			usuarioModel1.setChave("administrador");
			usuarioModel1.setDataCricaoAtualizacao(new Date());
			usuarioModel1.setUsuarioCriacaoAtualizacao("QUESTIONARIO_CARGA_BASICA");
			usuarioModel1.setIsAtivo(true);
			usuarioModel1.getPerfilModelList().add(perfilModel1);
			this.usuarioService.save(usuarioModel1);
		UsuarioModel usuarioModel2 = new UsuarioModel();
			usuarioModel2.setIdentificador("publico_alvo");
			usuarioModel2.setChave("publico_alvo");
			usuarioModel2.setDataCricaoAtualizacao(new Date());
			usuarioModel2.setUsuarioCriacaoAtualizacao("QUESTIONARIO_CARGA_BASICA");
			usuarioModel2.setIsAtivo(true);
			usuarioModel2.getPerfilModelList().add(perfilModel2);
			this.usuarioService.save(usuarioModel2);
		UsuarioModel usuarioModel3 = new UsuarioModel();
			usuarioModel3.setIdentificador("aplicador");
			usuarioModel3.setChave("aplicador");
			usuarioModel3.setDataCricaoAtualizacao(new Date());
			usuarioModel3.setUsuarioCriacaoAtualizacao("QUESTIONARIO_CARGA_BASICA");
			usuarioModel3.setIsAtivo(true);
			usuarioModel3.getPerfilModelList().add(perfilModel3);
			this.usuarioService.save(usuarioModel3);
		UsuarioModel usuarioModel4 = new UsuarioModel();
			usuarioModel4.setIdentificador("desenvolvimento");
			usuarioModel4.setChave("desenvolvimento");
			usuarioModel4.setDataCricaoAtualizacao(new Date());
			usuarioModel4.setUsuarioCriacaoAtualizacao("QUESTIONARIO_CARGA_BASICA");
			usuarioModel4.setIsAtivo(true);
			usuarioModel4.getPerfilModelList().add(perfilModel1);
			usuarioModel4.getPerfilModelList().add(perfilModel2);
			usuarioModel4.getPerfilModelList().add(perfilModel3);
			this.usuarioService.save(usuarioModel4);
	}

}
