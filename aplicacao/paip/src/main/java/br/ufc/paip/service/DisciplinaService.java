package br.ufc.paip.service;

import java.util.List;

import br.ufc.paip.bean.Disciplina;

public interface DisciplinaService {

	public Disciplina obterPorId(Integer id);

	public Disciplina obterPorNome(String nome);
	
	public Disciplina obterPorCodigo(String codigo);

	public List<Disciplina> obterDescendentes(Disciplina disciplina);

}
