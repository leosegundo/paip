package br.ufc.paip.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.paip.bean.Aluno;
import br.ufc.paip.bean.Usuario;
import br.ufc.paip.repository.AlunoRepository;
import br.ufc.paip.service.AlunoService;

@Service
public class AlunoServiceImpl implements AlunoService {
	
	@Autowired
	private AlunoRepository alunoRepository;

	@Override
	public boolean salvar(Aluno aluno) {
		alunoRepository.save(aluno);
		return true;
	}

	@Override
	public Aluno obter(Usuario usuario) {
		Aluno aluno = alunoRepository.findByUsuario(usuario);
		return aluno;
	}

	@Override
	public Aluno obterPorMatricula(String matricula) {
		return alunoRepository.findByMatricula(matricula);
	}
	
	

}
