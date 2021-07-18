package br.com.platormalancamento.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import br.com.platormalancamento.application.security.AutenticadorFilterSecurity;
import br.com.platormalancamento.application.security.ValidadorFilterSecurity;
import br.com.platormalancamento.application.service.UsuarioAutorizacaoService;

@EnableWebSecurity
@Configuration
public class AutenticadorConfiguration extends WebSecurityConfigurerAdapter {
	
	private final UsuarioAutorizacaoService usuarioAutorizacaoService;
	
	private final PasswordEncoder passwordEncoder;

	public AutenticadorConfiguration(UsuarioAutorizacaoService usuarioAutorizacaoService, PasswordEncoder passwordEncoder) {
		this.usuarioAutorizacaoService = usuarioAutorizacaoService;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(usuarioAutorizacaoService).passwordEncoder(passwordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/login").permitAll()
			.anyRequest().authenticated().and()
			.addFilter(new AutenticadorFilterSecurity(authenticationManager()))
			.addFilter(new ValidadorFilterSecurity(authenticationManager()))
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
			urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return urlBasedCorsConfigurationSource;
	}
	
}
