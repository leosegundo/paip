package br.ufc.paip.service;

import br.ufc.paip.bean.Aluno;
import br.ufc.paip.bean.Usuario;

public interface AlunoService {
	
	public boolean salvar(Aluno aluno);
	
	public Aluno obter(Usuario usuario);
	
	public Aluno obterPorMatricula(String matricula);

}
