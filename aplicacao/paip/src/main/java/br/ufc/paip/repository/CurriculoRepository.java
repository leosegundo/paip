package br.ufc.paip.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.paip.bean.Curriculo;

@Repository
@Transactional
public interface CurriculoRepository extends JpaRepository<Curriculo, Integer> {
	
	Curriculo findByCodigo(String codigo);
	List<Curriculo> findAll();

}
