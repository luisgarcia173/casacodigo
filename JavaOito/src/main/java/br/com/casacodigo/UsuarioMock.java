package br.com.casacodigo;

import java.util.List;

import com.google.common.collect.Lists;

public class UsuarioMock {

	public static List<Usuario> getUserList() {
		List<Usuario> usuarios = Lists.newArrayList(
			new Usuario("Luis Garcia", 150, false),
			new Usuario("Luis Carlos", 120, false), 
			new Usuario("Luis Nascimento", 190, false));
		
		return usuarios;
	}
}
