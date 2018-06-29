package br.ufc.paip.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity 
public class Usuario implements UserDetails{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	private String email;
	
	@Length(min = 5, message = "*Your password must have at least 5 characters")
	@NotEmpty(message = "*Please provide your password")
	private String senha;
	
	@NotEmpty(message = "*Please provide your name")
	private String nome;

	@Column(name = "papel")
	@CollectionTable(name = "papel_usuario")
	@Enumerated(EnumType.STRING)
	@ElementCollection(targetClass = Papel.class, fetch = FetchType.EAGER)
	private List<Papel> papeis;
	
	private boolean habilitado;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Papel> getPapeis() {
		return papeis;
	}

	public void setPapeis(List<Papel> papeis) {
		this.papeis = papeis;
	}
	
	public void adicionarPapel(Papel papel){
		if (this.papeis == null) {
			this.papeis = new ArrayList<Papel>();
		}
		if (papel != null) {
			this.papeis.add(papel);
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.papeis;				
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}
	
	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.habilitado;
	}
	
	public boolean isCoordenador() {
		return this.papeis.stream().filter(p -> p.equals(Papel.COORDENADOR))
				.findFirst().orElse(null) != null;
	}
	
	public boolean isOrientador() {
		return this.papeis.stream().filter(p -> p.equals(Papel.ORIENTADOR))
				.findFirst().orElse(null) != null;
	}
	
	public boolean isAluno() {
		return this.papeis.stream().filter(p -> p.equals(Papel.ALUNO))
				.findFirst().orElse(null) != null;
	}
	
	
	
}
