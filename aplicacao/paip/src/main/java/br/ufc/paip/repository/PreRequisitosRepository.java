package br.ufc.paip.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.paip.bean.Curriculo;
import br.ufc.paip.bean.Disciplina;
import br.ufc.paip.bean.PreRequisitos;

@Repository
@Transactional
public interface PreRequisitosRepository extends JpaRepository<PreRequisitos, Integer>{
	
	List<PreRequisitos> findByCurriculo(Curriculo curriculo);
	List<PreRequisitos> findByDisciplinaPreRequisito(Disciplina disciplina);
	

}
