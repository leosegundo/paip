
package br.ufc.paip.controller;

import static br.ufc.paip.util.Constants.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.paip.bean.Curso;
import br.ufc.paip.service.CursoService;

@Controller
@RequestMapping("/curso")
public class CursoController {
	
	@Autowired
	private CursoService cursoService;
	
	@PreAuthorize(PERMISSAO_ORIENTADOR)
    @RequestMapping("/")
    public String index(Model model){		
		model.addAttribute("cursos", cursoService.findAll());
        return "curso/index";
    }
	
	@PreAuthorize(PERMISSAO_COORDENADOR)
    @RequestMapping("/cadastrar")
    public String cadastrar(Model model){		
		model.addAttribute("curso", new Curso());
        return "curso/cadastro";
    }
	
	@PreAuthorize(PERMISSAO_COORDENADOR)
	@PostMapping("/cadastro")
	public String cadastrarSelecao(Curso curso,Authentication auth, Model model, RedirectAttributes redirect) {
		if(cursoService.salvar(curso)){
			return "redirect:/curso/";
		}else{
			return "redirect:/cadastrar";
		}
		
	}

}
