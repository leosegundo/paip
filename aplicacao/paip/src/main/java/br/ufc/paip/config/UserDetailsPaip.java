package br.ufc.paip.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.ufc.paip.bean.Usuario;
import br.ufc.paip.service.UsuarioService;
import static br.ufc.paip.util.Constants.LOGIN_INVALIDO;

@Service
public class UserDetailsPaip implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioService.findByEmail(username);
		if (usuario != null) {
			return usuario;
		} else {
			throw new UsernameNotFoundException(LOGIN_INVALIDO);
		}
	}

}
