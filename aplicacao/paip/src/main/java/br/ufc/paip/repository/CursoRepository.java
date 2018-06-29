
package br.ufc.paip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufc.paip.bean.Curso;

@Repository
@Transactional
public interface CursoRepository extends JpaRepository<Curso, Integer>{
	Curso findByCodigo(String codigo);
	Curso findByCodigoAndNome(String codigo, String nome);
	List<Curso> findAll();

}
