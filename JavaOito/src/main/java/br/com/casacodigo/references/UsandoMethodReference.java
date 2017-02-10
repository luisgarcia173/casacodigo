package br.com.casacodigo.references;

import static java.util.Comparator.comparing;
import static java.util.Comparator.nullsFirst;
import static java.util.Comparator.nullsLast;

import java.util.List;
import java.util.function.Supplier;

import br.com.casacodigo.Usuario;
import br.com.casacodigo.UsuarioMock;

public class UsandoMethodReference {

	public static void main(String[] args) {

		List<Usuario> usuarios = UsuarioMock.getUserList();
		
		// Tornando todos usuarios moderadores
		usuarios.forEach(u -> u.tornaModerador());

		// Usando method reference
		usuarios.forEach(Usuario::tornaModerador);
		
		// Compondo ordenação
		usuarios.sort(comparing(Usuario::getNome).thenComparingInt(Usuario::getPontos));
		
		// Jogando os nulos pro final
		usuarios.sort(nullsLast(comparing(Usuario::getNome)));
		usuarios.sort(nullsFirst(comparing(Usuario::getNome)));
		
		// Ordem reversa
		usuarios.sort(comparing(Usuario::getPontos).reversed());
		
		// Imprimindo valores
		usuarios.forEach(System.out::println);
		
		// Instanciando classes (construtor)
		Supplier<Usuario> criadorDeUsuarios = Usuario::new;
		criadorDeUsuarios.get();
		
		// c/ parametro (construtor)
		//Function<String, Usuario> criadorDeUsuariosComParam = Usuario::new;
		
		// c/ 2 parametros (construtor), ToIntBiFunction, IntBinaryOperator
		//BiFunction<String, Integer, Usuario> criadorDeUsuariosComParams = Usuario::new;
	}

}
