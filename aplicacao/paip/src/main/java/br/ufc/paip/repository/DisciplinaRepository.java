package br.ufc.paip.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.ufc.paip.bean.Disciplina;

@Repository
@Transactional
public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer>{

	//@Query("select d from Disciplina d where d.codigo= :codigo")
	Disciplina findByCodigo(String codigo);
	Disciplina findById(Integer id);
	
	@Query("select d from Disciplina d where d.nome= :nome")
	Disciplina findByNome(@Param("nome")String nome);
	
	List<Disciplina> findAll();
}
