package br.ufc.paip.service;

import br.ufc.paip.bean.Usuario;

public interface UsuarioService {
	public Usuario findByEmail(String email);
	public void salvarUsuario(Usuario usuario);
}
