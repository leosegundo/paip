package br.ufc.paip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufc.paip.bean.Aluno;
import br.ufc.paip.bean.CurriculoPessoal;
import br.ufc.paip.bean.Disciplina;

@Repository
@Transactional
public interface CurriculoPessoalRepository extends JpaRepository<CurriculoPessoal, Integer>{
	
	List<CurriculoPessoal> findAll();
	
	List<CurriculoPessoal> findByAluno(Aluno aluno);
	
	List<CurriculoPessoal> findByAlunoOrderBySemestre(Aluno aluno);
	
	List<CurriculoPessoal> findByAlunoAndObrigatoria(Aluno aluno, boolean obrigatoria);
	
	List<CurriculoPessoal> findByAlunoAndSemestre(Aluno aluno,Integer semestre);
	
	CurriculoPessoal findByAlunoAndDisciplina_Id(Aluno aluno, Integer disciplina_id);
	
	List<CurriculoPessoal> findByAlunoAndObrigatoriaOrderBySemestre(Aluno aluno, boolean obrigatoria);
	
	@Query("select cp from CurriculoPessoal cp where cp.aluno = :aluno order by cp.semestre, cp.posicao")
	List<CurriculoPessoal> encontrarOrdenadoPorPosicao(@Param("aluno")Aluno aluno);

	CurriculoPessoal findByAlunoAndDisciplina(Aluno aluno, Disciplina disciplina);

	List<CurriculoPessoal> findListByAlunoAndDisciplina(Aluno aluno, Disciplina disciplina);
	
	//@Query("select cp from CurriculoPessoal cp where cp.aluno = :aluno and cp.disciplina = :disciplina and cp.finalizado = :finalizado")
	//CurriculoPessoal findByAlunoAndDisciplinaAndFinalizado(@Param("aluno")Aluno aluno, @Param("disciplina")Disciplina disciplina, @Param("finalizado")boolean finalizado);

	List<CurriculoPessoal> findAllByAlunoAndDisciplina(Aluno aluno, Disciplina disciplina);
	
	@Query("select cp from CurriculoPessoal cp where cp.aluno = :aluno and cp.semestre = :semestre and cp.disciplina = :disciplina")
	List<CurriculoPessoal> findAllByAlunoAndDisciplinaAndSemestre(@Param("aluno")Aluno aluno,@Param("semestre")Integer semestre, @Param("disciplina")Disciplina disciplina);
	
	CurriculoPessoal findByAlunoAndDisciplinaAndFinalizado(Aluno aluno, Disciplina disciplina, boolean finalizado);
	
	

}
