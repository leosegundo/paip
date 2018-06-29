package br.ufc.paip.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.paip.bean.Curriculo;
import br.ufc.paip.bean.Disciplina;
import br.ufc.paip.bean.PreRequisitos;
import br.ufc.paip.repository.PreRequisitosRepository;
import br.ufc.paip.service.PreRequisitosService;

@Service
public class PreRequisitosServiceImpl implements PreRequisitosService{

	@Autowired
	private PreRequisitosRepository preRequisitosRepository;
	
	@Override
	public List<PreRequisitos> obterPorCurriculo(Curriculo curriculo) {
		List<PreRequisitos> preRequisitos = preRequisitosRepository.findByCurriculo(curriculo);
		return preRequisitos;
	}

	@Override
	public List<PreRequisitos> obterPorDisciplinaId(Curriculo curriculo, Integer disciplina) {
		List<PreRequisitos> todosPreRequsitos = obterPorCurriculo(curriculo);
		List<PreRequisitos> preRequisitos = new ArrayList<>();
		
		for (PreRequisitos pre : todosPreRequsitos) {
			if(pre.getDisciplina().getId().equals(disciplina)){
				preRequisitos.add(pre);
			}
		}
		
		return preRequisitos;
	}

	@Override
	public List<PreRequisitos> obterPosRequisitoPorDisciplina(Curriculo curriculo, Disciplina disciplina) {
		List<PreRequisitos> todosPreRequsitos = obterPorCurriculo(curriculo);
		List<PreRequisitos> preRequisitos = new ArrayList<>();
		
		for (PreRequisitos pre : todosPreRequsitos) {
			if(pre.getDisciplinaPreRequisito().equals(disciplina)){
				preRequisitos.add(pre);
			}
		}
		
		
		return preRequisitos;
	}
	

}
