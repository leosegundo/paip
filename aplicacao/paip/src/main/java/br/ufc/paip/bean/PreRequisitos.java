package br.ufc.paip.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class PreRequisitos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne(cascade=CascadeType.REMOVE)
	private Curso curso;
	
	@OneToOne(cascade=CascadeType.REMOVE)
	private Disciplina disciplina;
	
	@OneToOne
	private Disciplina disciplinaPreRequisito;
	
	@OneToOne
	private Curriculo curriculo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Disciplina getDisciplinaPreRequisito() {
		return disciplinaPreRequisito;
	}

	public void setDisciplinaPreRequisito(Disciplina disciplinaPreRequisito) {
		this.disciplinaPreRequisito = disciplinaPreRequisito;
	}

	public Curriculo getCurriculo() {
		return curriculo;
	}

	public void setCurriculo(Curriculo curriculo) {
		this.curriculo = curriculo;
	}
	
	
	
	

}
