package br.com.platormalancamento.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.platormalancamento.application.model.UsuarioAutenticacaoModel;
import br.com.platormalancamento.application.model.UsuarioModel;
import br.com.platormalancamento.application.repository.UsuarioRepository;

@Service
public class UsuarioAutenticacaoService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String identificador) throws UsernameNotFoundException {
		UsuarioModel usuarioModel = usuarioRepository.findByIdentificador(identificador);
		if(usuarioModel == null) {
			throw new UsernameNotFoundException(identificador);
		}
		return new UsuarioAutenticacaoModel(usuarioModel.getCodigo(), usuarioModel.getIdentificador(), usuarioModel.getSenha(), usuarioModel.getPerfilModelList());
	}

}
