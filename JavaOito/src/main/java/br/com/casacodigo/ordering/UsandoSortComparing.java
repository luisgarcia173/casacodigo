package br.com.casacodigo.ordering;

import java.util.Collections;
import static java.util.Comparator.*;
import java.util.List;

import br.com.casacodigo.Usuario;
import br.com.casacodigo.UsuarioMock;

public class UsandoSortComparing {

	public static void main(String[] args) {
		
		List<Usuario> usuarios = UsuarioMock.getUserList();
		
		// Usando Lamba
		//Comparator<Usuario> comparator = (u1, u2) -> u1.getNome().compareTo(u2.getNome());
		//Collections.sort(usuarios, comparator);
		
		// Ou
		Collections.sort(usuarios, (u1, u2) -> u1.getNome().compareTo(u2.getNome()));
		
		// Ou
		Collections.sort(usuarios, (u1, u2) -> String.CASE_INSENSITIVE_ORDER.compare(u1.getNome(), u2.getNome()));
		
		// Ou
		usuarios.sort((u1, u2) -> u1.getNome().compareTo(u2.getNome()));
		
		// Ou
		/*Comparator<Usuario> comparator2 = Comparator.comparing(u -> u.getNome());
		usuarios.sort(comparator2);*/
		
		// Ou
		//usuarios.sort(Comparator.comparing(u -> u.getNome()));
		
		// Ou c/ static importing
		usuarios.sort(comparing(u -> u.getNome()));
		
				
	}
	
}
