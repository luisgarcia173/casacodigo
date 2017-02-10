package br.com.casacodigo.streams;

import static java.util.Comparator.comparing;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import br.com.casacodigo.Usuario;
import br.com.casacodigo.UsuarioMock;

public class UsandoStreams {

	@SuppressWarnings({ "unused" })
	public static void main(String[] args) {
		
		List<Usuario> usuarios = UsuarioMock.getUserList(20);
		
		// Ordena os usuarios por pontos DESC
		usuarios.sort(comparing(Usuario::getPontos).reversed());
		
		// Torna os 10 primeiros com maior pontuacao moderadores 
		//usuarios.subList(0,10).forEach(Usuario::tornaModerador);
		
		// Ou
		usuarios.stream()
			.filter(u -> u.getPontos() > 180)
			.forEach(Usuario::tornaModerador);
		
		// Filtrar usuarios com mais de 120 pontos
		/*Stream<Usuario> stream = usuarios.stream();
		stream.filter(u -> {return u.getPontos() > 100});*/
		
		// Ou
		/*Stream<Usuario> listaFiltrada = usuarios.stream().filter(u -> u.getPontos() > 120);
		listaFiltrada.forEach(System.out::println);*/
		
		// Ou
		usuarios.stream()
			.filter(u -> u.getPontos() > 120)
			.forEach(System.out::println);
		
		// Listando somente os moderadores
		System.out.println("\nModeradores:");
		usuarios.stream()
			.filter(Usuario::isModerador)
			.forEach(System.out::println);
		
		// Retornando uma lista a partir da Stream
		List<Integer> pontos = usuarios.stream()
				.map(Usuario::getPontos)
				.collect(Collectors.toList());
		
		// Evitando AutoBoxing
		IntStream stream = usuarios.stream()
				.mapToInt(Usuario::getPontos);
		
		// Pegando media pontos
		/*OptionalDouble media = usuarios.stream()
				.mapToInt(Usuario::getPontos)
				.average();*/
		
		// Usando Optional para tratar casos de divisao infinita
		//double pontuacaoMedia = media.orElse(0.0);
		
		// Ou
		double pontuacaoMedia = usuarios.stream()
				.mapToInt(Usuario::getPontos)
				.average()
				//.orElse(0.0); // Caso não consiga calcular retorne zero
				.orElseThrow(IllegalStateException::new); // Usa Supplier para exceção
				//.ifPresent(valor -> janela.atualiza(valor)); Caso exista passamos um Consumer
		
	}
	
}
