package br.ufc.paip.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import br.ufc.paip.bean.Usuario;
import br.ufc.paip.service.UsuarioService;


@ControllerAdvice
public class UsuarioControllerAdvice {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@ModelAttribute
	public void addUsuario(Model model, Authentication auth){
		if (auth!=null && auth.isAuthenticated()){
			Usuario usuario = usuarioService.findByEmail(auth.getName());
			model.addAttribute("usuario_", usuario);			
		}
	}
}
