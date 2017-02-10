package br.com.casacodigo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public @Data class Usuario {

	private String nome;
	private int pontos;
	private boolean moderador;
	
	public void tornaModerador() { this.moderador = true; }
}
