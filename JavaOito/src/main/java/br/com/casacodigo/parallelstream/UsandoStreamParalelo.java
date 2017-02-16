package br.com.casacodigo.parallelstream;

import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import br.com.casacodigo.Usuario;
import br.com.casacodigo.UsuarioMock;

public class UsandoStreamParalelo {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		List<Usuario> usuarios = UsuarioMock.getUserList(20);

		// Gerando lista filtrada e ordenanda com stream normal (unica Thread)
		// Usar com streams pequenas
		List<Usuario> filtradosOrdenados = usuarios.stream()
			.filter(u -> u.getPontos() > 100)
			.sorted(Comparator.comparing(Usuario::getNome))
			.collect(Collectors.toList());
		
		// Gerando lista filtrada e ordenanda com stream paralela (JVM decide qts threads serao executadas e como juntar resultado final)
		// Usar com streams de grande volume, evitar overhead
		List<Usuario> filtradosOrdenadosParalela = usuarios.parallelStream()
			.filter(u -> u.getPontos() > 100)
			.sorted(Comparator.comparing(Usuario::getNome))
			.collect(Collectors.toList());
		
		// Teste de performance com o parallel fazendo soma de um bilhao
		Instant inicio = Instant.now();
		long sum = LongStream.range(0, 1_000_000_000)
			.parallel() // Comentar para ver diferença
			.filter(x -> x % 2 == 0)
			.sum();
		Instant fim = Instant.now();
		System.out.println("Soma: " + sum + " Duracao: (" + Duration.between(inicio, fim).toMillis() + ")");
	}

}
