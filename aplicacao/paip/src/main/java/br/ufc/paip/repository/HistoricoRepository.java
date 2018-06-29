package br.ufc.paip.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.paip.bean.Historico;


@Repository
@Transactional
public interface HistoricoRepository extends JpaRepository<Historico, Integer> {
	

}
