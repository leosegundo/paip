package br.ufc.paip.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.paip.bean.Aluno;
import br.ufc.paip.bean.Curriculo;
import br.ufc.paip.bean.Disciplina;
import br.ufc.paip.bean.OfertaDisciplina;
import br.ufc.paip.repository.OfertaDisciplinaRepository;
import br.ufc.paip.service.CurriculoPessoalService;
import br.ufc.paip.service.OfertaDisciplinaService;

@Service
public class OfertaDisciplinaServiceImpl implements OfertaDisciplinaService {
	
	@Autowired
	private OfertaDisciplinaRepository ofertaDisciplinaRepository;
	
	@Autowired
	private CurriculoPessoalService curriculoPessoalService;

	@Override
	public List<Disciplina> obterPorCurriculo(Curriculo curriculo) {
		List<OfertaDisciplina> ofertas = ofertaDisciplinaRepository.findByCurriculo(curriculo);
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();
		Disciplina d = new Disciplina();
		
		for (OfertaDisciplina ofertaDisciplina : ofertas) {
			d = ofertaDisciplina.getDisciplina();
			disciplinas.add(d);
		}
		
		return disciplinas;
	}

	@Override
	public Map<Integer, List<OfertaDisciplina>> obterOfertasPorSemestre(Curriculo curriculo) {
		Map<Integer, List<OfertaDisciplina>> ofertasDisciplinasPorSemestre = new HashMap<>();
		List<OfertaDisciplina> ofertaCurriculo = ofertaDisciplinaRepository.findByCurriculoAndObrigatoria(curriculo, true);
		for( OfertaDisciplina cp : ofertaCurriculo) {
			Integer semestre = cp.getSemestre();
			if(ofertasDisciplinasPorSemestre.containsKey(semestre)) {
				List<OfertaDisciplina> ofertaSemestre = ofertasDisciplinasPorSemestre.get(semestre);
				ofertaSemestre.add(cp);
				ofertasDisciplinasPorSemestre.put(semestre, ofertaSemestre);
			}else {
				List<OfertaDisciplina> ofertaSemestre = new ArrayList<>();
				ofertaSemestre.add(cp);
				ofertasDisciplinasPorSemestre.put(semestre, ofertaSemestre);
			}
		}
		return ofertasDisciplinasPorSemestre;
	}
	
	

	@Override
	public List<Disciplina> obterOptativas(Curriculo curriculo, Aluno aluno) {
		List<OfertaDisciplina> ofertas = ofertaDisciplinaRepository.findByCurriculoAndObrigatoria(curriculo, false);
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();
		Disciplina disciplina;
		
		for (OfertaDisciplina ofertaDisciplina : ofertas) {
			disciplina = ofertaDisciplina.getDisciplina();
			if(curriculoPessoalService.buscarPorDisciplina(aluno, disciplina)==null){
				disciplinas.add(disciplina);
			}
		}
		
		return disciplinas;
	}

	@Override
	public OfertaDisciplina obterPorId(Integer id) {
		return ofertaDisciplinaRepository.findById(id);
	}
	
	

}
