package br.com.casacodigo;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

public class UsuarioMock {

	public static List<Usuario> getUserList() {
		return getUserList(10);
	}
	
	public static List<Usuario> getUserList(int tamanho) {
		List<Usuario> usuarios = Lists.newArrayList();
		Random r = new Random();
		for (int i = 0; i < tamanho; i++) {
			usuarios.add(new Usuario("Luis Garcia ["+i+"]", (r.nextInt(100) + 100), false));
		}
		return usuarios;
	}
}
