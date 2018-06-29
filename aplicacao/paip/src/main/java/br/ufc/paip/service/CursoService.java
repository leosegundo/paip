package br.ufc.paip.service;

import java.util.List;

import br.ufc.paip.bean.Curso;

public interface CursoService {
	
	public List<Curso> findAll();
	public boolean salvar(Curso curso);

}
