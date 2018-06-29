package br.ufc.paip.bean;


import org.springframework.security.core.GrantedAuthority;
 
public enum Papel implements GrantedAuthority{
	ORIENTADOR, COORDENADOR, ALUNO;

    @Override
    public String getAuthority() {
        return this.toString();
    }
	
	

}
