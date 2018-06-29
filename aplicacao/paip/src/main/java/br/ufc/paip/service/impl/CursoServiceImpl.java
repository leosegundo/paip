package br.ufc.paip.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.paip.bean.Curso;
import br.ufc.paip.repository.CursoRepository;
import br.ufc.paip.service.CursoService;

@Service
public class CursoServiceImpl implements CursoService {
	@Autowired
	private CursoRepository cursoRepository;
	
	@Override
	public List<Curso> findAll() {
		return cursoRepository.findAll();
	}
	@Override
	public boolean salvar(Curso curso) {
		Curso verificacao = cursoRepository.findByCodigoAndNome(curso.getCodigo(), curso.getNome());
		if(verificacao == null){
			cursoRepository.save(curso);
			return true;
		}
		return false;
	}
}
