package br.com.casacodigo.predicate;

import java.util.List;

import br.com.casacodigo.Usuario;
import br.com.casacodigo.UsuarioMock;

public class UsandoPredicates {

	public static void main(String[] args) {
		
		List<Usuario> usuarios = UsuarioMock.getUserList();
		
		// Classe anonima
		/*Predicate<Usuario> predicado = new Predicate<Usuario>() {
			@Override
			public boolean test(Usuario u) {
				return u.getPontos() > 160;
			}
		};
		
		usuarios.removeIf(predicado);*/
		
		usuarios.removeIf(u -> u.getPontos() > 160);
		usuarios.forEach(u -> System.out.println(u));
		
	}
	
}
