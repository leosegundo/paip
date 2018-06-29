package br.ufc.paip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.paip.bean.Aluno;
import br.ufc.paip.bean.CurriculoPessoal;
import br.ufc.paip.bean.Disciplina;
import br.ufc.paip.bean.Papel;
import br.ufc.paip.bean.Usuario;
import br.ufc.paip.service.AlunoService;
import br.ufc.paip.service.CurriculoPessoalService;
import br.ufc.paip.service.CursoService;
import br.ufc.paip.service.OfertaDisciplinaService;
import br.ufc.paip.service.UsuarioService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

@Controller
public class PaipController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private OfertaDisciplinaService ofertaDisciplinaService;
	
	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private CurriculoPessoalService curriculoPessoalService;
	
	@RequestMapping({"","/"})
	public String index(Model model, Authentication auth){
		Usuario usuario = usuarioService.findByEmail(auth.getName());
		System.out.println("chegou?");
		if(usuario.isAluno()){
			return "redirect:aluno/";			
		}
		else if(usuario.isOrientador()){
			return "redirect:orientador/";
		}
		else{
			return "index";
		}
	}
	
	@RequestMapping("/cadastro")
	public String cadastro(Model model){
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("aluno", new Aluno());
		model.addAttribute("cursos", cursoService.findAll());
		return "usuario/cadastro";
	}
	
	
	@PostMapping("/cadastro")
	public String cadastrarSelecao(Usuario usuario,Aluno aluno ,Authentication auth, Model model, RedirectAttributes redirect) {
		/*if((alunoService.obterPorMatricula(aluno.getMatricula()) != null)){
			System.out.println("entrous aqui");
			return "#";
		}else {
			System.out.println("mãaao");
			usuario.adicionarPapel(Papel.ALUNO);
			usuario.setHabilitado(true);
			usuarioService.salvarUsuario(usuario);
			aluno.setUsuario(usuario);
			alunoService.salvar(aluno);
			curriculoPessoalService.curriculoAluno(alunoService.obter(usuarioService.findByEmail(usuario.getEmail())));
			
			Map<Integer, List<CurriculoPessoal>> semestresAluno = curriculoPessoalService.curriculoSemeste(aluno);
			System.err.println("passou aqui");
			List<Disciplina> optativas = ofertaDisciplinaService.obterOptativas(aluno.getCurriculo(), aluno);
			System.err.println("passou 2");
			model.addAttribute("aluno", aluno);
			model.addAttribute("chaves", new ArrayList<>(semestresAluno.keySet()));
			model.addAttribute("disciplinas", semestresAluno);
			model.addAttribute("optativas", optativas);
			
			return "aluno/index";
		}*/
		
		usuario.adicionarPapel(Papel.ALUNO);
		usuario.setHabilitado(true);
		usuarioService.salvarUsuario(usuario);
		aluno.setUsuario(usuario);
		alunoService.salvar(aluno);
		//adicionando curriculo ao aluno
		curriculoPessoalService.curriculoAluno(alunoService.obter(usuarioService.findByEmail(usuario.getEmail())));
		return "redirect:";
		
		//return "redirect:/login";
		//return "redirect:";
		//return entrar(usuario);
	}
}
