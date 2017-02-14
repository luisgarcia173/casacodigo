package br.com.casacodigo.mapping;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import br.com.casacodigo.Usuario;
import br.com.casacodigo.UsuarioMock;

public class UsandoMaps {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		final String path = "C:/workspaces/casacodigo/casacodigo/JavaOito/src/main/java/br/com/casacodigo";
		
		IntStream chars = Files.find(Paths.get(path),Integer.MAX_VALUE,(filePath, fileAttr) -> fileAttr.isRegularFile())
			//.filter(p -> p.toString().endsWith(".java"))
			//.map(p -> lines(p)) // retorna stream
			.flatMap(p -> lines(p))
			.flatMapToInt((String s) -> s.chars()); // conta qtd caracters linha
			//.forEach(System.out::println); // imprime cada linha
		
		// Recupera os arquivos do path cada linha texto
		Stream<String> strings = Files.find(Paths.get(path),Integer.MAX_VALUE,(filePath, fileAttr) -> fileAttr.isRegularFile())
			.filter(p -> p.toString().endsWith(".java"))
			.flatMap(p -> lines(p));
		
		// Conta qtd linha de cada arquivo
		LongStream lines = Files.find(Paths.get(path),Integer.MAX_VALUE,(filePath, fileAttr) -> fileAttr.isRegularFile())
			.filter(p -> p.toString().endsWith(".java"))
			.mapToLong(p -> lines(p).count());
		
		// Conta linha por arquivo
		Map<Path, Long> linesMap = Files.find(Paths.get(path),Integer.MAX_VALUE,(filePath, fileAttr) -> fileAttr.isRegularFile())
			.filter(p -> p.toString().endsWith(".java"))
			.collect(Collectors.toMap(p -> p,
									  p -> lines(p).count()));
		System.out.println(linesMap);
		
		// Guarda conteudo arquivo por arquivo
		Map<Path, List<String>> content = Files.find(Paths.get(path),Integer.MAX_VALUE,(filePath, fileAttr) -> fileAttr.isRegularFile())
			.filter(p -> p.toString().endsWith(".java"))
			.collect(Collectors.toMap(Function.identity(), // equivalente p -> p
									  p -> lines(p).collect(Collectors.toList())));
		System.out.println(content);
		
		// Organizando usuarios em map por nome
		List<Usuario> usuarios = UsuarioMock.getUserList(2);
		Map<String, Usuario> nameToUser = usuarios.stream()
			.collect(Collectors.toMap(Usuario::getNome,
									  Function.identity()));  // equivalente p -> p
		System.out.println(nameToUser);
	}
	
	// Workaround pra tratar Exception fora lambda
	static Stream<String> lines(Path p) {
		try {
			return Files.lines(p);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

}
