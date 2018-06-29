package br.ufc.paip.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static br.ufc.paip.util.Constants.*;



/**
 * Created by allan on 04/05/2017.
 */

@Controller
@RequestMapping("orientador")
public class OrientadorController {
	@PreAuthorize(PERMISSAO_ORIENTADOR)
    @RequestMapping("/")
    public String index(){
        return "orientador/index";
    }
	@PreAuthorize(PERMISSAO_ORIENTADOR)
    @RequestMapping("/aluno/{id}")
    public String visualizarOrientado(){
        return "orientador/aluno";
    }
}
