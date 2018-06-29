package br.ufc.paip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufc.paip.bean.Curriculo;
import br.ufc.paip.bean.Curso;
import br.ufc.paip.bean.OfertaDisciplina;

@Repository
@Transactional
public interface OfertaDisciplinaRepository extends JpaRepository<OfertaDisciplina, Integer>{

	List<OfertaDisciplina> findBySemestre(Integer semestre); 
	List<OfertaDisciplina> findAll();
	List<OfertaDisciplina> findByCurriculoAndCurso(Curriculo curriculo, Curso curso);
	List<OfertaDisciplina> findByCurriculo(Curriculo curriculo);
	List<OfertaDisciplina> findByCurriculoAndObrigatoria(Curriculo curriculo, Boolean obrigatoria);
	OfertaDisciplina findById(Integer id);
}
