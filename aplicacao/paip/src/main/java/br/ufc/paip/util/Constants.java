package br.ufc.paip.util;

public class Constants {
	
	public static final String LOGIN_INVALIDO = "Usuário não encontrado e/ou senha inválidos";
	
	// Papéis
		public static final String ALUNO = "ALUNO";		
		public static final String ORIENTADOR = "ORIENTADOR";		
		public static final String COORDENADOR = "COORDENADOR";

		// Geral
		public static final String INFO = "info";

		public static final String ERRO = "erro";

		// Permissões
		public static final String PERMISSAO_COORDENADOR = "hasAuthority('COORDENADOR')";
		public static final String PERMISSAO_ORIENTADOR = "hasAuthority('ORIENTADOR')";
		public static final String PERMISSAO_ALUNO = "hasAuthority('ALUNO')";

}
