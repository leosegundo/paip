
package br.ufc.paip.controller;
//AAA

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.ufc.paip.bean.Aluno;
import br.ufc.paip.bean.CurriculoPessoal;
import br.ufc.paip.bean.Disciplina;
import br.ufc.paip.bean.OfertaDisciplina;
import br.ufc.paip.bean.PreRequisitos;
import br.ufc.paip.bean.Usuario;
import br.ufc.paip.service.AlunoService;
import br.ufc.paip.service.CurriculoPessoalService;
import br.ufc.paip.service.DisciplinaService;
import br.ufc.paip.service.OfertaDisciplinaService;
import br.ufc.paip.service.PreRequisitosService;
import br.ufc.paip.service.UsuarioService;
import br.ufc.paip.util.Response;

@Controller
@RequestMapping("aluno")
public class AlunoController {
	
	@Autowired
	private AlunoService alunoService;
	 
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PreRequisitosService preRequisitosService;
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	@Autowired
	private CurriculoPessoalService curriculoPessoalService;
	
	@Autowired
	private OfertaDisciplinaService ofertaDisciplinaService;
	
	@RequestMapping({"/", ""})
	public String index(Model model,  Authentication auth){ 
		System.out.println("aquiiii");
		Aluno aluno = alunoService.obter(usuarioService.findByEmail(auth.getName()));
		Map<Integer, List<CurriculoPessoal>> semestresAluno = curriculoPessoalService.curriculoSemeste(aluno);
		List<Disciplina> optativas = ofertaDisciplinaService.obterOptativas(aluno.getCurriculo(), aluno);
		model.addAttribute("aluno", aluno);
		model.addAttribute("chaves", new ArrayList<>(semestresAluno.keySet()));
		model.addAttribute("disciplinas", semestresAluno);
		model.addAttribute("optativas", optativas);
		return "aluno/index";
		
	}
	
	
	@RequestMapping({"/comparar"})
	public String checarHistorio(Model model,  Authentication auth){		
		Aluno aluno = alunoService.obter(usuarioService.findByEmail(auth.getName()));
		Map<Integer, List<OfertaDisciplina>> ofertaSemestre = ofertaDisciplinaService.obterOfertasPorSemestre(aluno.getCurriculo());
		model.addAttribute("curriculoAluno", curriculoPessoalService.curriculoAluno(aluno));
		model.addAttribute("chaves", new ArrayList<>(ofertaSemestre.keySet()));
		model.addAttribute("disciplinas", ofertaSemestre);
		//model.addAttribute("optativas", ofertaDisciplinaService.obterOptativas(aluno.getCurriculo()));	
		return "aluno/verificar-curso";
		
	}
	
	@PostMapping("/dependencia")
	@ResponseBody
	public Response get(Integer disciplina, Authentication auth) {
		Disciplina disciplinaLocal = disciplinaService.obterPorId(disciplina);
		List<Disciplina> disciplinas = disciplinaService.obterDescendentes(disciplinaLocal);
		if(disciplinas.size() > 0) {
			return new Response("DONE", disciplinas);
		}
		else {
			return new Response();
		}
	}
	@PostMapping("/disciplina")
	@ResponseBody
	public Response alterarCurriculoPessoal(Integer disciplina, Integer semestre, Authentication auth) {
		Usuario usuario = usuarioService.findByEmail(auth.getName());
		Aluno aluno = alunoService.obter(usuario);
		
		CurriculoPessoal curriculoPessoal = curriculoPessoalService.buscarPorDisciplinaId(aluno, disciplina);
			
			if(curriculoPessoal.getSemestreOriginal() > semestre) {
				curriculoPessoal.setAtrasado(false);
			}else{
				curriculoPessoal.setAtrasado(true);
			}
			
			if(curriculoPessoalService.buscarPorSemestre(aluno, curriculoPessoal.getSemestre()).size() > 1){
				curriculoPessoal.setSemestre(semestre);
				curriculoPessoalService.salvarCurriculoAluno(curriculoPessoal);
				
				empurrarDisciplina(aluno, semestre);
				
				Response response = new Response("DONE", curriculoPessoal);
				return response;
			}
			
			Response response = new Response("UNDONE", null);
			return response;
			
	}
	
	@PostMapping("/semestre")
	@ResponseBody
	public Response ordernarCurriculoPessoal(Integer semestre,String disciplinasId,Integer valor, Authentication auth) {
		Aluno aluno = alunoService.obter(usuarioService.findByEmail(auth.getName()));
		Response response = new Response();
		
		String[] idDisciplinas = disciplinasId.split(",");
		System.out.println(idDisciplinas.length);
		List<Integer> disciplinas = new ArrayList<>();
		for(int i=0; i<idDisciplinas.length;i++) {
			disciplinas.add(Integer.parseInt(idDisciplinas[i]));
		}
		
		if(curriculoPessoalService.atualizarPosicaoSemestre(aluno, semestre, disciplinas)) {
			response.setStatus("DONE");
			response.setData(curriculoPessoalService.buscarPorSemestre(aluno, semestre));
		}else {
			response.setStatus("UNDONE");
			response.setData("DEU RUIM!");
			return response;
		}
		
		if(valor>0){
			empurrarDisciplina(aluno, semestre);
		}
		
		return response;
	}
	
	@PostMapping("/sessionCurriculo")
	@ResponseBody
	public Response getSessionCurriculo(Integer a, Authentication auth) {
		Usuario usuario = usuarioService.findByEmail(auth.getName());
		Aluno aluno = alunoService.obter(usuario);
		
		List<CurriculoPessoal> curriculoPessoal = curriculoPessoalService.curriculoAluno(aluno);
		
		Response response = new Response("DONE", curriculoPessoal);
		return response;
		
	}
	
	@PostMapping("/sessionPreRequisito")
	@ResponseBody
	public Response getSessionPreRequisito(Integer a, Authentication auth) {
		Usuario usuario = usuarioService.findByEmail(auth.getName());
		Aluno aluno = alunoService.obter(usuario);
		
		List<PreRequisitos> preRequisitos = preRequisitosService.obterPorCurriculo(aluno.getCurriculo());
		
		Response response = new Response("DONE", preRequisitos);
		return response;
		
	}
	
	
	@PostMapping("/historico")
	@ResponseBody
	public Response inserirHistorico(String disciplinaNome, String valorNota,Integer semestre, Authentication auth) {
		Usuario usuario = usuarioService.findByEmail(auth.getName());
		Aluno aluno = alunoService.obter(usuario);
	
		String[] nomeDisciplinas = disciplinaNome.split(",");
		String[] nota = valorNota.split(",");
		
		List<String> disciplinas = new ArrayList<>();
		List<Double> notas = new ArrayList<>();
		
		for(int i=0; i<nomeDisciplinas.length;i++) {
			nomeDisciplinas[i] = nomeDisciplinas[i].replace('"', ' ');
			nomeDisciplinas[i] = nomeDisciplinas[i].substring(1, nomeDisciplinas[i].length()-1); 
			disciplinas.add(nomeDisciplinas[i]);
		}
			
	
		for(int i=0; i < nota.length;i++) {
			nota[i] = nota[i].substring(1, nota[i].length()-1);
			if(!nota[i].isEmpty()){
				notas.add(Double.parseDouble(nota[i]));
			}
		}
		
		for(int i=0;i<disciplinas.size();i++){
			CurriculoPessoal curriculoPessoal; 
			Disciplina disciplina = disciplinaService.obterPorId(Integer.parseInt(disciplinas.get(i)));
			
			curriculoPessoal = curriculoPessoalService.buscarPorDisciplinaNaoFinalizado(aluno, disciplina);
			if(curriculoPessoal != null) {
				List<CurriculoPessoal> curriculos = curriculoPessoalService.buscarListPorDisciplina(aluno, disciplina);
				System.out.println("tam: " + curriculos.size());
				for(CurriculoPessoal c : curriculos) {
					if(c.getSemestre() == semestre) {
						curriculoPessoal = c;
					}
					if(c.getSemestre() > semestre) {
						curriculoPessoalService.deletarCurriculo(c);
					}
				}
				curriculoPessoal.setNota(notas.get(i));
				curriculoPessoal.setFinalizado(true);
				curriculoPessoalService.salvarCurriculoAluno(curriculoPessoal);			
				
				if(curriculoPessoal.getNota()<5){
					curriculoPessoalService.duplicarCurriculo(curriculoPessoal);
					empurrarDisciplina(aluno, 0);
				}
			}else {
				//curriculoPessoal = curriculoPessoalService.buscarPorDisciplina(aluno, disciplina);
				List<CurriculoPessoal> curriculos = curriculoPessoalService.buscarListPorDisciplina(aluno, disciplina);
				for(CurriculoPessoal c : curriculos) {
					if(c.getSemestre() == semestre) {
						curriculoPessoal = c;
					}
					if(c.getSemestre() > semestre) {
						curriculoPessoalService.deletarCurriculo(c);
					}
				}
				curriculoPessoal.setNota(notas.get(i));
				curriculoPessoal.setFinalizado(true);
				curriculoPessoalService.salvarCurriculoAluno(curriculoPessoal);
				
				if(curriculoPessoal.getNota()<5){
					curriculoPessoalService.duplicarCurriculo(curriculoPessoal);
					empurrarDisciplina(aluno, 0);
				}
						
			}
			
		}
		
		Response response = new Response("DONE",aluno);
		return response;
	}
	
	@PostMapping("/salvarOptativa")
	@ResponseBody
	public Response salvarOptativa(Integer idOptativa, Integer idDisciplina, Authentication auth){
		Aluno aluno = alunoService.obter(usuarioService.findByEmail(auth.getName()));
		Disciplina disciplina = disciplinaService.obterPorId(idDisciplina);
		CurriculoPessoal curriculoOptativa = curriculoPessoalService.buscarPorDisciplinaId(aluno, idOptativa);
		
		curriculoOptativa.setDisciplina(disciplina);
		curriculoPessoalService.salvarCurriculoAluno(curriculoOptativa);
		
		Response response = new Response("DONE",aluno);
		return response;
		
	}
	
	
	public void updateCurriculo(CurriculoPessoal curriculo) {
		curriculoPessoalService.salvarCurriculoAluno(curriculo);
	}
	
	public void empurrarDisciplina(Aluno aluno, Integer semestre){
		int i = 1;
		
		while(i>0){
			List<CurriculoPessoal> curriculoPessoal = curriculoPessoalService.buscarPorSemestre(aluno, i);

			if(!curriculoPessoal.isEmpty()){
				for (CurriculoPessoal curriculo : curriculoPessoal) {
					if(curriculo.getSemestre() > curriculo.getSemestreOriginal()) {
						curriculo.setAtrasado(true);
					}else {
						curriculo.setAtrasado(false);
					}
					
					List<PreRequisitos> preRequsitos = preRequisitosService.obterPorDisciplinaId(aluno.getCurriculo(), curriculo.getDisciplina().getId());
					List<CurriculoPessoal> curriculosPreRequisito = curriculoPessoalService.buscarCurriculosPorPreRequisitos(aluno, preRequsitos);
					
					System.err.println("semestre:" + i +" - "+curriculo.getDisciplina().getNome() +" - qtd pre: "+curriculosPreRequisito.size());
					
					for (CurriculoPessoal curriculo2 : curriculosPreRequisito) {
						try{
							if(curriculo2.getSemestre()>= i && !curriculo2.isFinalizado()){
								if(curriculoPessoalService.buscarCargaHorariaPorSemestre(aluno, i+1) <= 320){
									curriculo.setSemestre(i+1);
								}
								else{
									curriculo.setSemestre(i+2);
								}
								updateCurriculo(curriculo);
							}
							else if(curriculo2.getSemestre()!= null && curriculo.getSemestre()<=curriculo2.getSemestre()){
								if(curriculoPessoalService.buscarCargaHorariaPorSemestre(aluno, curriculo2.getSemestre()+1) <= 320){
									curriculo.setSemestre(curriculo2.getSemestre()+1);
								}
								else{
									curriculo.setSemestre(curriculo2.getSemestre()+2);
								}
								updateCurriculo(curriculo);
							}
						}
						catch(NullPointerException exception){
							
						}
					}
				}
			}
			else{
				break;
			}
			i = i+1;
		}
		
	}
	
	public List<PreRequisitos> verificarPreRequisitosNoSemestre(Integer disciplina,List<CurriculoPessoal> curriculoPessoal, Aluno aluno) {
		
		List<PreRequisitos> pre_Requisitos = preRequisitosService.obterPorDisciplinaId(aluno.getCurriculo(), disciplina);
		
		List<PreRequisitos> preRequisitos = new ArrayList<>();
		
		for (CurriculoPessoal curriculo : curriculoPessoal) {
			for (PreRequisitos pre : pre_Requisitos){
				if(curriculo.getDisciplina().equals(pre.getDisciplinaPreRequisito())){
					preRequisitos.add(pre);
				}
			}
		}
		return preRequisitos;
	}
	
	public List<PreRequisitos> verificarPosRequisitoNoSemestre(Disciplina disciplina,List<CurriculoPessoal> curriculoPessoal, Aluno aluno) {
		
		List<PreRequisitos> pos_Requisitos = preRequisitosService.obterPosRequisitoPorDisciplina(aluno.getCurriculo(), disciplina);
		
		List<PreRequisitos> posRequisitos = new ArrayList<>();
		
		for (CurriculoPessoal curriculo : curriculoPessoal) {
			for (PreRequisitos pos : pos_Requisitos){
				if(curriculo.getDisciplina().equals(pos.getDisciplina())){
					posRequisitos.add(pos);
				}
			}
		}
		
		return posRequisitos;
	}
	
}
