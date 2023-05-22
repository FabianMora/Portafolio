package com.example.tienda.services;

import java.util.List;
import java.util.Optional;

import com.example.tienda.model.Usuario;


public interface UsuarioServices {
	public Usuario createUser(Usuario usuario);

	public Usuario updateUsuario(Usuario usuario);

	public String deleteUsuario(String correo);
	
	public Optional<Usuario>  findByCorreoAndContrasena(String correo,String contrasena);
	
	public List<Usuario> finAll();
	
	public Optional<Usuario> findByCorreo(String correo);
	
}
