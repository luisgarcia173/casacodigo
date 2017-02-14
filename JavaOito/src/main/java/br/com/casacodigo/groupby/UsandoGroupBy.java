package br.com.casacodigo.groupby;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.casacodigo.Usuario;
import br.com.casacodigo.UsuarioMock;

public class UsandoGroupBy {

	public static void main(String[] args) {
		List<Usuario> usuarios = UsuarioMock.getUserList(1, 2, 173);

		// Usando Java 7
		Map<Integer, List<Usuario>> pontuacao7 = new HashMap<>();
		for (Usuario u : usuarios) {
			if (!pontuacao7.containsKey(u.getPontos())) {
				pontuacao7.put(u.getPontos(), new ArrayList<>());
			}
			pontuacao7.get(u.getPontos()).add(u);
		}
		System.out.println(pontuacao7);
		
		// Usando Java 8 (computeIfAbsent: cria se nao houver a chave passada)
		Map<Integer, List<Usuario>> pontuacao8 = new HashMap<>();
		for(Usuario u: usuarios) {
			pontuacao8.computeIfAbsent(u.getPontos(), user -> new ArrayList<>()).add(u);
		}
		System.out.println(pontuacao8);
		
		// Agrupando com Streams
		Map<Integer, List<Usuario>> pontuacaoGroupingBy = usuarios.stream()
			.collect(Collectors.groupingBy(Usuario::getPontos));
		System.out.println(pontuacaoGroupingBy);
		
		// Particionar usuarios por quem eh moderador e quem nao eh (Eficiente com booleans)
		Map<Boolean, List<Usuario>> moderadores = usuarios.stream()
			.collect(Collectors.partitioningBy(Usuario::isModerador));
		System.out.println(moderadores);
		
		// Particionar usuarios por quem eh moderador e quem nao eh, usando collector pra extrair somente nome
		Map<Boolean, List<String>> nomesPorTipo = usuarios.stream()
			.collect(Collectors.partitioningBy(
				Usuario::isModerador, Collectors.mapping(Usuario::getNome, Collectors.toList())));
		System.out.println(nomesPorTipo);
		
		// Particionar usuarios por quem eh moderador e quem nao eh, somando os pontos
		Map<Boolean, Integer> pontuacaoPorTipo = usuarios.stream()
			.collect(Collectors.partitioningBy(
				Usuario::isModerador, Collectors.summingInt(Usuario::getPontos)));
		System.out.println(pontuacaoPorTipo);
	
		// Usando Collector para concatenar nome dos usuarios
		String nomes = usuarios.stream()
			.map(Usuario::getNome)
			.collect(Collectors.joining(", "));
		System.out.println(nomes);
	}

}
