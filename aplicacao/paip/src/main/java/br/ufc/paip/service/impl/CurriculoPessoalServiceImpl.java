package br.ufc.paip.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.paip.bean.Aluno;
import br.ufc.paip.bean.CurriculoPessoal;
import br.ufc.paip.bean.Disciplina;
import br.ufc.paip.bean.OfertaDisciplina;
import br.ufc.paip.bean.PreRequisitos;
import br.ufc.paip.repository.CurriculoPessoalRepository;
import br.ufc.paip.repository.DisciplinaRepository;
import br.ufc.paip.repository.OfertaDisciplinaRepository;
import br.ufc.paip.service.CurriculoPessoalService;
import br.ufc.paip.service.DisciplinaService;
import br.ufc.paip.service.OfertaDisciplinaService;

@Service
public class CurriculoPessoalServiceImpl implements CurriculoPessoalService {
	
	@Autowired
	private CurriculoPessoalRepository curriculoPessoalRepository;
	
	@Autowired
	private OfertaDisciplinaRepository ofertaDisciplinaRepository;
	
	@Autowired
	private OfertaDisciplinaService ofertaDisciplinaService;
	
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	
	@Autowired
	private DisciplinaService disciplinaService;

	@Override
	public List<CurriculoPessoal> curriculoAluno(Aluno aluno) {
		List<CurriculoPessoal> curriculoPessoal = curriculoPessoalRepository.findByAluno(aluno);
		if(curriculoPessoal.size()==0) {
			curriculoNovoAluno(aluno);
			curriculoPessoal = curriculoPessoalRepository.findByAluno(aluno);
		}
		return curriculoPessoal;
	}

	@Override
	public void curriculoNovoAluno(Aluno aluno) {
		
		List<OfertaDisciplina> ofertaDisciplinas = ofertaDisciplinaRepository.findByCurriculoAndObrigatoria(aluno.getCurriculo(), true);
		for (OfertaDisciplina ofertaDisciplina: ofertaDisciplinas) {
			CurriculoPessoal curriculoPessoal = new CurriculoPessoal();
			curriculoPessoal.setDisciplina(ofertaDisciplina.getDisciplina());
			curriculoPessoal.setAluno(aluno);
			List<CurriculoPessoal> a = curriculoPessoalRepository.findByAlunoAndSemestre(aluno, ofertaDisciplina.getSemestre());
			curriculoPessoal.setSemestre(ofertaDisciplina.getSemestre());
			curriculoPessoal.setSemestreOriginal(ofertaDisciplina.getSemestre());
			curriculoPessoal.setObrigatoria(ofertaDisciplina.isObrigatoria());
			curriculoPessoal.setPosicao(a.size()+1);
			curriculoPessoalRepository.save(curriculoPessoal);			
		}
		int semestres[] = {1,1,3,3};
		int pos =0;
		int count =1;
		for(int inicio = 5; inicio<9;inicio++) {
			for(int i = semestres[pos]; i>0; i--) {
				Disciplina disc_optativa = disciplinaService.obterPorCodigo("OPTATIVA"+count);
				CurriculoPessoal curriculoPessoal = new CurriculoPessoal();
				curriculoPessoal.setDisciplina(disc_optativa);
				curriculoPessoal.setAluno(aluno);
				List<CurriculoPessoal> a = curriculoPessoalRepository.findByAlunoAndSemestre(aluno, inicio);
				System.out.println(a.size());
				curriculoPessoal.setSemestre(inicio);
				curriculoPessoal.setSemestreOriginal(inicio);
				curriculoPessoal.setObrigatoria(false);	
				curriculoPessoal.setPosicao(a.size()+1);
				curriculoPessoalRepository.save(curriculoPessoal);
				count++;
			}
			pos++;
		}
		
		

	}

	@Override
	public CurriculoPessoal buscarPorDisciplinaId(Aluno aluno, Integer idDisciplina) {
		Disciplina disciplina = disciplinaRepository.findById(idDisciplina);
		return curriculoPessoalRepository.findByAlunoAndDisciplina(aluno, disciplina);
		//return curriculoPessoalRepository.findByAlunoAndDisciplinaAndFinalizado(aluno, disciplina,false);
	}

	@Override
	public void salvarCurriculoAluno(CurriculoPessoal curriculoPessoal) {
		curriculoPessoalRepository.save(curriculoPessoal);
	}
	
	@Override
	public void updateCurriculoAluno(List<CurriculoPessoal> curriculoPessoal) {
		curriculoPessoalRepository.save(curriculoPessoal);
	}

	@Override
	public List<CurriculoPessoal> buscarPorSemestre(Aluno aluno,Integer semestre) {
		return curriculoPessoalRepository.findByAlunoAndSemestre(aluno,semestre);
	}

	@Override
	public Map<Integer, List<CurriculoPessoal>> curriculoSemeste(Aluno aluno) {
		
		Map<Integer, List<CurriculoPessoal>> curriculoAlunoSemeste = new HashMap<>();
		for( CurriculoPessoal cp : curriculoPessoalRepository.encontrarOrdenadoPorPosicao(aluno)) {
			Integer semestre = cp.getSemestre();
			if(curriculoAlunoSemeste.containsKey(semestre)) {
				List<CurriculoPessoal> curriculoSemestre = curriculoAlunoSemeste.get(semestre);
				curriculoSemestre.add(cp);
				curriculoAlunoSemeste.put(semestre, curriculoSemestre);
			}else {
				List<CurriculoPessoal> curriculoSemestre = new ArrayList<>();
				curriculoSemestre.add(cp);
				curriculoAlunoSemeste.put(semestre, curriculoSemestre);
			}
		}
		return curriculoAlunoSemeste;
	}

	@Override
	public boolean atualizarPosicaoSemestre(Aluno aluno, Integer semestre, List<Integer> idDisciplinas) {
		boolean verificacao = false;
		for(int i = 0; i< idDisciplinas.size(); i++) {
			Integer idDisciplina = idDisciplinas.get(i);
			List<CurriculoPessoal> curriculoPessoal = null;
			curriculoPessoal = curriculoPessoalRepository.findAllByAlunoAndDisciplina(aluno, disciplinaService.obterPorId(idDisciplina));
			System.out.println("eheheh :" + curriculoPessoal.get(0).getDisciplina().getNome());
			CurriculoPessoal curriculoAux = null;
			
			//Quando adicionada um disciplina optativa
			if(curriculoPessoal.isEmpty()) {
				CurriculoPessoal curriculoPessoal2 = new CurriculoPessoal();
				Disciplina disciplina = disciplinaRepository.findById(idDisciplina);
				curriculoPessoal2.setDisciplina(disciplina);
				curriculoPessoal2.setObrigatoria(false);
				curriculoPessoal2.setAluno(aluno);
				curriculoPessoal2.setSemestre(semestre);
				curriculoPessoal2.setSemestreOriginal(semestre);
				curriculoPessoal2.setPosicao(i+1);
				if(curriculoPessoalRepository.save(curriculoPessoal2) != null) verificacao = true;
			}
			else{
				System.out.println("entrou aqui");
				for(CurriculoPessoal curriculo : curriculoPessoal) {
					if(!curriculo.isFinalizado()){
						curriculoAux = curriculo;
						
					}
				}
				
				if(curriculoAux == null){
					return false;
				}
				else{
					System.out.println("aaaqui" + curriculoAux.toString());
					if(curriculoAux.getSemestreOriginal() >= semestre) {
						curriculoAux.setAtrasado(false);
					}else{
						curriculoAux.setAtrasado(true);
					}
					List<CurriculoPessoal> cAuxiliar = buscarPorSemestre(aluno, curriculoAux.getSemestre());
					List<CurriculoPessoal> cAuxiliar2 = buscarPorSemestre(aluno, semestre);
					System.out.println(cAuxiliar.size()+" " +  cAuxiliar2.size());
					//if(cAuxiliar.size() > 1 &&  cAuxiliar2.size() < 8){
						//System.out.println("Carao");
						curriculoAux.setSemestre(semestre);
						curriculoAux.setPosicao(i+1);
						if(curriculoPessoalRepository.save(curriculoAux) != null) {
							verificacao = true;
						}
					//}else {
					//	return false;
					//}
					
				}
			}
			
		}
		return verificacao;
	}

	@Override
	public void setCurriculoFinalizado(Aluno aluno, Disciplina disciplina) {
		CurriculoPessoal curriculoPessoal = curriculoPessoalRepository.findByAlunoAndDisciplina(aluno, disciplina);
		curriculoPessoal.setFinalizado(true);
		curriculoPessoalRepository.save(curriculoPessoal);
	}

	@Override
	public CurriculoPessoal buscarPorDisciplina(Aluno aluno, Disciplina disciplina) {
		CurriculoPessoal curriculoPessoal =  curriculoPessoalRepository.findByAlunoAndDisciplina(aluno, disciplina);
		return curriculoPessoal;
	}

	@Override
	public List<CurriculoPessoal> buscarCurriculosPorPreRequisitos(Aluno aluno,
			List<PreRequisitos> preRequisitos) {
		List<CurriculoPessoal> curriculos = new ArrayList<CurriculoPessoal>();

		for (PreRequisitos p : preRequisitos) {
			//curriculos.add(buscarPorDisciplina(aluno, p.getDisciplinaPreRequisito()));
			curriculos.add(buscarPorDisciplinaNaoFinalizado(aluno, p.getDisciplinaPreRequisito()));
		}
		
		return curriculos;
	}

	@Override
	public Integer buscarCargaHorariaPorSemestre(Aluno aluno, Integer semestre) {
		int carga = 0;
		List<CurriculoPessoal> curriculos = curriculoPessoalRepository.findByAlunoAndSemestre(aluno, semestre);
		for(CurriculoPessoal c : curriculos){
			System.err.println(c.getDisciplina().getNome() + " --- " + c.getDisciplina().getCargaHoraria());
			carga += Integer.parseInt(c.getDisciplina().getCargaHoraria());
		}
		System.err.println("semestre : "+semestre +" c: "+ carga);
		return carga;
	}

	@Override
	public void duplicarCurriculo(CurriculoPessoal curriculo) {
		CurriculoPessoal novoCurriculo = new CurriculoPessoal();
		
		novoCurriculo.setAluno(curriculo.getAluno());
		novoCurriculo.setDisciplina(curriculo.getDisciplina());
		novoCurriculo.setObrigatoria(curriculo.isObrigatoria());
		novoCurriculo.setPosicao(curriculo.getPosicao());
		novoCurriculo.setSemestre(curriculo.getSemestre()+1);
		novoCurriculo.setSemestreOriginal(curriculo.getSemestreOriginal());
		
		curriculoPessoalRepository.save(novoCurriculo);

	}

	@Override
	public CurriculoPessoal buscarPorDisciplinaNaoFinalizado(Aluno aluno, Disciplina disciplina) {
		List<CurriculoPessoal> disciplinaNaoFinalizada = curriculoPessoalRepository.findAllByAlunoAndDisciplina(aluno, disciplina);
			for(CurriculoPessoal curriculoPessoal : disciplinaNaoFinalizada) {
				System.out.println(curriculoPessoal);
				if(!curriculoPessoal.isFinalizado()){
					return curriculoPessoal;
				}
			}
			return null;
	}

	@Override
	public void deletarCurriculo(CurriculoPessoal curriculo) {
		curriculoPessoalRepository.delete(curriculo);
	}

	@Override
	public List<CurriculoPessoal> buscarListPorDisciplina(Aluno aluno, Disciplina disciplina) {
		//return null;
		return curriculoPessoalRepository.findListByAlunoAndDisciplina(aluno, disciplina);
	}
}
