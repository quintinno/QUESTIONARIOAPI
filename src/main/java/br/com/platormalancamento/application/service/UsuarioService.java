package br.com.platormalancamento.application.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import br.com.platormalancamento.application.utility.SegurancaUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.platormalancamento.application.model.UsuarioModel;
import br.com.platormalancamento.application.repository.UsuarioRepository;

@Service
public class UsuarioService implements UsuarioServiceInterface, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UsuarioModel save(UsuarioModel usuarioModel) {
		usuarioModel.setChave(passwordEncoder.encode(usuarioModel.getChave()));
		usuarioModel.setIsAtivo(true);
		usuarioModel.setDataCricaoAtualizacao(new Date());
		usuarioModel.setUsuarioCriacaoAtualizacao("CADASTRO_USUARIO_SISTEMA");
		return this.usuarioRepository.save(usuarioModel);
	}

	@Override
	public List<UsuarioModel> findAll() {
		return this.usuarioRepository.findAll();
	}

}
