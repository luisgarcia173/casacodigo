package br.com.casacodigo.ordering;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

import br.com.casacodigo.Usuario;
import br.com.casacodigo.UsuarioMock;

public class UsandoSortFunction {

	public static void main(String[] args) {
		
		List<Usuario> usuarios = UsuarioMock.getUserList();
		
		Function<Usuario, Integer> extraiPontos = u -> u.getPontos(); // AutoBoxing int to Integer
		Comparator<Usuario> comparator = Comparator.comparing(extraiPontos);
		usuarios.sort(comparator);
		
		// Avoiding autoboxing
		ToIntFunction<Usuario> extraiPontosInt = u -> u.getPontos();
		Comparator<Usuario> comparatorInt = Comparator.comparingInt(extraiPontosInt);
		usuarios.sort(comparatorInt);
		
		// Ou 
		usuarios.sort(Comparator.comparingInt(u -> u.getPontos()));
	}
	
}
