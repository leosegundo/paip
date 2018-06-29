package br.ufc.paip.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.paip.bean.Curriculo;
import br.ufc.paip.bean.Curso;
import br.ufc.paip.repository.CurriculoRepository;
import br.ufc.paip.repository.CursoRepository;

@Controller
@RequestMapping(path="/curriculo/")
public class CurriculoController {
	
	@Autowired
	private CursoRepository cRepo;
	
	@Autowired
	private CurriculoRepository crRepo;
	
	@RequestMapping(path="/")
	public String index(){
		return "curriculo/index_curriculo";
		
	}
	
	@RequestMapping(path = "/inserirCurriculoFormulario")
	public String inserirCurriculoFormulario(Model model){
		List<Curso> cursos = this.cRepo.findAll();
		model.addAttribute("cursos", cursos);
		return "curriculo/inserir_curriculo_formulario";
	}
	
	@RequestMapping(path="/inserirCurriculo")
	public String inserirCurriculo(Curriculo curriculos,  HttpServletRequest req){
		Curso c = new Curso();
		c = this.cRepo.findByCodigo(req.getParameter("cursoValor"));
		curriculos.setCurso(c);
		this.crRepo.save(curriculos);
		return "curriculo/index_curriculo";
	}

}
