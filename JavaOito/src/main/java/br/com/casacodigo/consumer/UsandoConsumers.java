package br.com.casacodigo.consumer;

import java.util.List;
import java.util.function.Consumer;

import br.com.casacodigo.Usuario;
import br.com.casacodigo.UsuarioMock;

public class UsandoConsumers {

	public static void main(String[] args) {
		
		List<Usuario> usuarios = UsuarioMock.getUserList();
		
		Consumer<Usuario> mostraMensagem = u ->	System.out.print("Antes de imprimir cada nome: ");
		Consumer<Usuario> imprimeNome = u -> System.out.println(u.getNome());
		
		usuarios.forEach(mostraMensagem.andThen(imprimeNome));
		
	}
	
}
