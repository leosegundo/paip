package br.ufc.paip.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class OfertaDisciplina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
	private Disciplina disciplina;
	
	@OneToOne(cascade=CascadeType.REMOVE)
	private Curso curso;
	
	@OneToOne(cascade=CascadeType.REMOVE)
	private Curriculo curriculo;
	
	private boolean obrigatoria;
	
	private Integer semestre;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public boolean isObrigatoria() {
		return obrigatoria;
	}

	public void setObrigatoria(boolean obrigatoria) {
		this.obrigatoria = obrigatoria;
	}

	public Integer getSemestre() {
		return semestre;
	}

	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}

	public Curriculo getCurriculo() {
		return curriculo;
	}

	public void setCurriculo(Curriculo curriculo) {
		this.curriculo = curriculo;
	}

	@Override
	public String toString() {
		return "OfertaDisciplina [id=" + id + ", disciplina=" + disciplina + ", curso=" + curso + ", curriculo="
				+ curriculo + ", obrigatoria=" + obrigatoria + ", semestre=" + semestre + "]";
	}
	
	
	
	

}
