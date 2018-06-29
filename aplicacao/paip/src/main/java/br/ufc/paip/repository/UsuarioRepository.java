package br.ufc.paip.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.paip.bean.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	Usuario findByEmail(String email);
}
