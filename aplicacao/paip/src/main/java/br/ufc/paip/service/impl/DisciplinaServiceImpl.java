package br.ufc.paip.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.paip.bean.Disciplina;
import br.ufc.paip.bean.PreRequisitos;
import br.ufc.paip.repository.DisciplinaRepository;
import br.ufc.paip.repository.PreRequisitosRepository;
import br.ufc.paip.service.DisciplinaService;

@Service
public class DisciplinaServiceImpl implements DisciplinaService{

	@Autowired
	private DisciplinaRepository disciplinaRepository;
	
	@Autowired
	private PreRequisitosRepository preRequisitosRepository;
	
	@Override
	public Disciplina obterPorId(Integer id) {
		return disciplinaRepository.findById(id);
	}

	@Override
	public Disciplina obterPorNome(String nome) {
		String a = nome.substring(1, nome.length()-1);
		//System.err.println(a);
		//System.err.println(disciplinaRepository.findByNome(a));
		
		return disciplinaRepository.findByNome(a);
	}

	public List<Disciplina> obterDescendentes(Disciplina disciplina) {
		List<Disciplina> resultado = new ArrayList<>();
		List<PreRequisitos> preRequesitos = preRequisitosRepository.findByDisciplinaPreRequisito(disciplina);
		for(PreRequisitos pr: preRequesitos) {
			resultado.add(pr.getDisciplina());
		}
		return resultado;

	}

	@Override
	public Disciplina obterPorCodigo(String codigo) {
		return disciplinaRepository.findByCodigo(codigo);
	}

}
