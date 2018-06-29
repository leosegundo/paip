package br.ufc.paip.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Disciplina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique=true)
	private String codigo;
	
	private String nome;
	
//	@OneToMany
//	private List<Historico> historicos;

	private String cargaHoraria;

	public Integer getId() {
		return id;
	}

	public void setId(Integer idDisciplina) {
		this.id = idDisciplina;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

//	public List<Historico> getHistoricos() {
		//return historicos;
//	}

	//public void setHistoricos(List<Historico> historicos) {
		//this.historicos = historicos;
	//}

	public String getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(String cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	@Override
	public String toString() {
		return "Disciplina [id: " + id + ", codigo: " + codigo + ", nome: " + nome + "]";
	}
	
	
	
	
}
