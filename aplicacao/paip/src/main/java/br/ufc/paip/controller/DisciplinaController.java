package br.ufc.paip.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.paip.bean.Curriculo;
import br.ufc.paip.bean.Disciplina;
import br.ufc.paip.bean.OfertaDisciplina;
import br.ufc.paip.repository.CurriculoRepository;
import br.ufc.paip.repository.DisciplinaRepository;
import br.ufc.paip.repository.OfertaDisciplinaRepository;

@Controller
@RequestMapping("disciplina")
public class DisciplinaController{
	
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	
	@Autowired
	private CurriculoRepository curriculoRepository;
	
	@Autowired
	private OfertaDisciplinaRepository ofertaDisciplinaRepository;
	
	@RequestMapping(path="/")
	public String index(Model model){
		model.addAttribute("disciplinas", disciplinaRepository.findAll());
		return "disciplina/index-disciplina";
		
	}
	
	@PostMapping(path = "/listarTodasDisciplinas")
	public String listarTodasDisciplinas(Model model){  
		List<Disciplina> a = this.disciplinaRepository.findAll();
		if(a != null){
			model.addAttribute("disciplina", a);
		}
		
		return "disciplina/lista_disciplinas";
	}
	
	@RequestMapping(path = "/inserirDisciplinaFormulario")
	public String inserirDisciplinaFormulario(Model model){
		//List<Curso> cursos = this.cRepo.findAll();
		List<Curriculo> curriculos = this.curriculoRepository.findAll();
		model.addAttribute("curriculos", curriculos);
		return "disciplina/inserir_disciplina_formulario";
	}
	
	@PostMapping(path = "/inserirDisciplina")
	public String inserirDisciplina(Disciplina disciplina, HttpServletRequest req){
		this.disciplinaRepository.save(disciplina);
		
		Curriculo curriculo = this.curriculoRepository.findByCodigo(req.getParameter("curriculoValor"));
		Disciplina disciplinaOferta = this.disciplinaRepository.findByCodigo(disciplina.getCodigo());
		
		OfertaDisciplina ofertaDisciplina = new OfertaDisciplina();
		ofertaDisciplina.setCurriculo(curriculo);
		ofertaDisciplina.setDisciplina(disciplinaOferta);
		ofertaDisciplina.setSemestre(Integer.parseInt(req.getParameter("semestre")));
		ofertaDisciplina.setObrigatoria(Boolean.parseBoolean(req.getParameter("obrigatoria")));
		
		this.ofertaDisciplinaRepository.save(ofertaDisciplina);
		
		return "disciplina/index_disciplina";
	}
	
	

}
