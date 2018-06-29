package br.ufc.paip.service;

import java.util.List;
import java.util.Map;

import br.ufc.paip.bean.Aluno;
import br.ufc.paip.bean.CurriculoPessoal;
import br.ufc.paip.bean.Disciplina;
import br.ufc.paip.bean.PreRequisitos;

public interface CurriculoPessoalService {
	
	public List<CurriculoPessoal> curriculoAluno(Aluno aluno);
	public void curriculoNovoAluno(Aluno aluno);
	public CurriculoPessoal buscarPorDisciplinaId(Aluno aluno, Integer disciplina);
	public CurriculoPessoal buscarPorDisciplina(Aluno aluno, Disciplina disciplina);
	public List<CurriculoPessoal> buscarPorSemestre(Aluno aluno,Integer semestre);
	public void salvarCurriculoAluno(CurriculoPessoal curriculoPessoal);
	public void updateCurriculoAluno(List<CurriculoPessoal> curriculoPessoal);
	public Map<Integer, List<CurriculoPessoal>> curriculoSemeste(Aluno aluno);
	//public boolean atualizarPosicaoSemestre(Aluno aluno, Integer semestre, List<Integer> idDisciplinas);
	public boolean atualizarPosicaoSemestre(Aluno aluno, Integer semestre, List<Integer> idDisciplinas);
	public void setCurriculoFinalizado(Aluno aluno, Disciplina disciplina);
	public List<CurriculoPessoal> buscarCurriculosPorPreRequisitos(Aluno aluno, List<PreRequisitos> preRequisitos);
	public Integer buscarCargaHorariaPorSemestre(Aluno aluno,Integer semestre);
	public void duplicarCurriculo(CurriculoPessoal curriculo);
	public CurriculoPessoal buscarPorDisciplinaNaoFinalizado(Aluno aluno, Disciplina disciplina);
	
	public List<CurriculoPessoal> buscarListPorDisciplina(Aluno aluno, Disciplina disciplina);
	
	public void deletarCurriculo(CurriculoPessoal curriculo);

}
