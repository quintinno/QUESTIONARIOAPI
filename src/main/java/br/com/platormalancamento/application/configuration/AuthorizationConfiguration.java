package br.com.platormalancamento.application.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.platormalancamento.application.filter.AuthenticationFilter;
import br.com.platormalancamento.application.service.UsuarioAutenticacaoService;
import br.com.platormalancamento.application.utility.AutenticacaoUtility;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthorizationConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private UsuarioAutenticacaoService usuarioAutenticacaoService;
	
	@Autowired
	private AutenticacaoUtility autenticacaoUtility;
	
	private static final String[] PUBLIC_URL = { };
	
	private static final String[] PUBLIC_URL_LEITURA = {
			"/usuario/**",
			"/perfil/**"
	};
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
		// Habilitar endpoints no ambiente de teste como por exemplo o bando H2
		if(Arrays.asList(environment.getActiveProfiles()).contains("teste")) {
			httpSecurity.headers().frameOptions().disable();
		}
		
		// Para as URL publicas permitir acesso. Para URL privadas, solicitar autenticacao.
		httpSecurity.authorizeRequests()
		.antMatchers(HttpMethod.GET, PUBLIC_URL_LEITURA).permitAll() // Permitir Leitura de dados (GET) dos endpoints
		.antMatchers(PUBLIC_URL).permitAll().anyRequest().authenticated();
		
		// Desabilitrar ataques CSRF
		httpSecurity.cors().and().csrf().disable();
		
		// Garantir que a aplicacao trabalhe sem guardar sessao
		httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		httpSecurity.addFilter(new AuthenticationFilter(authenticationManager(), autenticacaoUtility));
	
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(usuarioAutenticacaoService).passwordEncoder(bCryptPasswordEncoder());
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		// Permitir acesso de multiplas fontes
		final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
			urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return urlBasedCorsConfigurationSource;
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
}
