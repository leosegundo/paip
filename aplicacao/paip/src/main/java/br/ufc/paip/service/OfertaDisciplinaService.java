package br.ufc.paip.service;

import java.util.List;
import java.util.Map;

import br.ufc.paip.bean.Aluno;
import br.ufc.paip.bean.Curriculo;
import br.ufc.paip.bean.Disciplina;
import br.ufc.paip.bean.OfertaDisciplina;

public interface OfertaDisciplinaService {
	
	public List<Disciplina> obterPorCurriculo(Curriculo curriculo);
	public List<Disciplina> obterOptativas(Curriculo curriculo, Aluno aluno);
	public Map<Integer, List<OfertaDisciplina>>obterOfertasPorSemestre(Curriculo curriculo);
	
	public OfertaDisciplina obterPorId(Integer id);
	


}
