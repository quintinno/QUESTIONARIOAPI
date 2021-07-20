package br.com.platormalancamento.application.service;

import br.com.platormalancamento.application.model.PerfilModel;
import br.com.platormalancamento.application.model.UsuarioModel;
import br.com.platormalancamento.application.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Service
public class UsuarioService implements UsuarioServiceInterface, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private PerfilService perfilService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UsuarioModel save(UsuarioModel usuarioModel) {
		this.configurarUsuarioModel(usuarioModel);
		return this.usuarioRepository.save(usuarioModel);
	}

	private void configurarUsuarioModel(UsuarioModel usuarioModel) {
		usuarioModel.setChave(this.bCryptPasswordEncoder.encode(usuarioModel.getChave()));
		usuarioModel.setIsAtivo(true);
		usuarioModel.setDataCricaoAtualizacao(new Date());
		usuarioModel.setUsuarioCriacaoAtualizacao("CADASTRO_USUARIO_SISTEMA");
		if(usuarioModel.getPerfilModelList().isEmpty()) {
			usuarioModel.getPerfilModelList().add(recuperarPerfilUsuario());
		}
	}

	private PerfilModel recuperarPerfilUsuario() {
		return this.perfilService.findByNomePerfil("PUBLICO_ALVO");
	}

	@Override
	public List<UsuarioModel> findAll() {
		return this.usuarioRepository.findAll();
	}

	@Transactional
	public UsuarioModel recuperarUsuario(UsuarioModel usuarioModel) {
		return this.usuarioRepository.findByIdentificador(usuarioModel.getIdentificador());
	}

}
