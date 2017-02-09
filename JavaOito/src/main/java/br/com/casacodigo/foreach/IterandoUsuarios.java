package br.com.casacodigo.foreach;

import java.util.List;

import br.com.casacodigo.Usuario;
import br.com.casacodigo.UsuarioMock;

public class IterandoUsuarios {

	public static void main(String[] args) {
		
		List<Usuario> usuarios = UsuarioMock.getUserList();
		
		System.out.println("Foreach antigo: ");
		for(Usuario u : usuarios) { System.out.println(u.getNome()); }
		
		System.out.println("\nForeach novo: ");
		usuarios.forEach(u -> System.out.println(u.getNome()));
		
	}
	
}
