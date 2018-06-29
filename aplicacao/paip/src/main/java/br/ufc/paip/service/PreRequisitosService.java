package br.ufc.paip.service;

import java.util.List;

import br.ufc.paip.bean.Curriculo;
import br.ufc.paip.bean.Disciplina;
import br.ufc.paip.bean.PreRequisitos;

public interface PreRequisitosService {
	
	public List<PreRequisitos> obterPorCurriculo(Curriculo curriculo);
	public List<PreRequisitos> obterPorDisciplinaId(Curriculo curriculo, Integer disciplina);
	public List<PreRequisitos> obterPosRequisitoPorDisciplina(Curriculo curriculo, Disciplina disciplina);

}
