package br.com.platormalancamento.application.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.platormalancamento.application.model.UsuarioAutorizacaoModel;
import br.com.platormalancamento.application.model.UsuarioModel;
import br.com.platormalancamento.application.repository.UsuarioRepository;

@Component
public class UsuarioAutorizacaoService implements UserDetailsService {
	
	private final UsuarioRepository usuarioRepository;

	public UsuarioAutorizacaoService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String identificador) throws UsernameNotFoundException {
		Optional<UsuarioModel> usuarioModelOptional = Optional.ofNullable(this.usuarioRepository.findByIdentificador(identificador));
		if(usuarioModelOptional.isEmpty()) {
			throw new UsernameNotFoundException("USUARIO [" + identificador + "] NAO ENCONTRADO!");
		}
		return new UsuarioAutorizacaoModel(usuarioModelOptional);
	}

	public UsuarioRepository getUsuarioRepository() {
		return usuarioRepository;
	}

}
