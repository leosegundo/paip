package br.ufc.paip.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.paip.bean.Historico;
import br.ufc.paip.repository.HistoricoRepository;
import br.ufc.paip.service.HistoricoService;

@Service
public class HistoricoServiceImpl implements HistoricoService{
	
	@Autowired
	private HistoricoRepository historicoRepository;

	@Override
	public void salvar(Historico historico) {
		historicoRepository.save(historico);
	}
	
	

}
