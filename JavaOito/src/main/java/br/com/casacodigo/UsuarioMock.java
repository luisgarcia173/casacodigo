package br.com.casacodigo;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

public class UsuarioMock {

	public static List<Usuario> getUserList() {
		return getUserList(5, 0 , 0);
	}
	
	public static List<Usuario> getUserList(int iterations) {
		return getUserList(iterations, 0 , 0);
	}
	
	public static List<Usuario> getUserList(int count, int value) {
		return getUserList(5, count, value);
	}
	
	public static List<Usuario> getUserList(int iterations, int count, int value) {
		List<Usuario> usuarios = Lists.newArrayList();
		Random r = new Random();
		for (int i = 0; i < iterations; i++) {
			usuarios.add(new Usuario("Luis Garcia ["+i+"]", (r.nextInt(100) + 100), false));
		}
		if (count > 0) {
			for (int i = 0; i < count; i++) {
				usuarios.add(new Usuario("Luis Garcia [x"+i+"x]", value, true));
			}
		}
		return usuarios;
	}
}
