package br.com.casacodigo.streams;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.casacodigo.Usuario;
import br.com.casacodigo.UsuarioMock;

public class UsandoStreamsAvancado {

	public static void main(String[] args) {
		
		List<Usuario> usuarios = UsuarioMock.getUserList(20);
		
		// Ordenando por stream, Stream não altera estado do objeto, se quiser uma lista deve usar Collector
		List<Usuario> filtradosOrdenados = usuarios.stream()
			.filter(u -> u.getPontos() > 100)
			.sorted(Comparator.comparing(Usuario::getNome)) // lazy -> operacoes intermediarias
			.collect(Collectors.toList()); // forca eager -> operacao terminal
		
		// Encontra primero com atenda o Filtro
		Optional<Usuario> usuarioOptional = usuarios.stream()
				.filter(u -> u.getPontos() > 100)
				.sorted(Comparator.comparing(Usuario::getNome))
				.findAny();
		
		// Encontra primero com atenda o Filtro na ordem da lista eh percorrida
		Optional<Usuario> usuarioOptionalFirst = usuarios.stream()
				.filter(u -> u.getPontos() > 100)
				.findFirst();
		
		// Peek faz com que a stream execute algo cada vez que o filtro for atendido
		usuarios.stream()
			.filter(u -> u.getPontos() > 100)
			.peek(System.out::println) // Retorna novo stream, soh executado quando encontra operacao terminal
			.findAny(); // Forca optional, nao dando problema com nullpointer (operacao terminal)
		
		// Mesmo retornando somente um registro, peek por ser stateful processa td a lista
		usuarios.stream()
			.sorted(Comparator.comparing(Usuario::getNome))
			.peek(System.out::println)
			.findAny();
		
		// Usando iterator, para modificar stream
		usuarios.stream().iterator()
			.forEachRemaining(System.out::println);
		
		// Usando filter
		boolean hasModerator = usuarios.stream()
				.anyMatch(Usuario::isModerador); // allMatch, noneMatch.
		
		
		
	}
	
}