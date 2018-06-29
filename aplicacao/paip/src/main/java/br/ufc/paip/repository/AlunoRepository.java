package br.ufc.paip.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.paip.bean.Aluno;
import br.ufc.paip.bean.Usuario;

@Repository
@Transactional
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
	
	Aluno findByMatricula(String matricula);
	
	List<Aluno> findAll();
	
	Aluno findByUsuario(Usuario usuario);
	

}
