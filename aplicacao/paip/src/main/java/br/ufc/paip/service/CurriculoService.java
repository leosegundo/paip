package br.ufc.paip.service;

import java.util.List;

import br.ufc.paip.bean.Aluno;
import br.ufc.paip.bean.Curriculo;

public interface CurriculoService {
	
	public List<Curriculo> curriculoAluno(Aluno aluno);

}
