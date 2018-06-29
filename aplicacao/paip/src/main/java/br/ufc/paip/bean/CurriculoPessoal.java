
package br.ufc.paip.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class CurriculoPessoal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	//@OneToOne
	@ManyToOne
	private Disciplina disciplina;
	
	@OneToOne
	private Aluno aluno;

	private Integer semestre;
	
	private boolean obrigatoria;
	
	private boolean finalizado;
	
	private Integer posicao;
	
	private Double nota;
	
	private Integer semestreOriginal;
	
	private boolean atrasado;

	public Integer getSemestre() {
		return semestre;
	}

	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isObrigatoria() {
		return obrigatoria;
	}

	public void setObrigatoria(boolean obrigatoria) {
		this.obrigatoria = obrigatoria;
	}

	public Integer getPosicao() {
		return posicao;
	}

	public void setPosicao(Integer posicao) {
		this.posicao = posicao;
	}
	
	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}
	
	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}
	
	

	public Integer getSemestreOriginal() {
		return semestreOriginal;
	}

	public void setSemestreOriginal(Integer semestreOriginal) {
		this.semestreOriginal = semestreOriginal;
	}

	public boolean isAtrasado() {
		return atrasado;
	}

	public void setAtrasado(boolean atrasado) {
		this.atrasado = atrasado;
	}

	@Override
	public String toString() {
		return "CurriculoPessoal [id=" + id + ", disciplina=" + disciplina.getNome() + ", aluno=" + aluno.getUsuario().getNome() + ", semestre="
				+ semestre + ", obrigatoria=" + obrigatoria + ", posicao=" + posicao + "]";
	}
	
	
	
	
	
	
	
	
	

}
