package com.example.tienda.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tienda.model.Usuario;
import com.example.tienda.repository.UsuarioRepository;

@Service
public class UsuarioServicesImpl implements UsuarioServices {
	@Autowired
	UsuarioRepository repositorio;

	@Override
	public Usuario createUser(Usuario usuario) {
		Usuario creado = repositorio.insert(usuario);
		creado.setContrasena("");
		return creado;

	}
	@Override
	public Optional<Usuario>  findByCorreo(String correo) {
		return repositorio.findByCorreo(correo);
	}

	@Override
	public Usuario updateUsuario(Usuario usuario) {
		if (usuario == null) {
			return null;
		}
		if (usuario.getCorreo() == null) {
			return null;
		}
		Optional<Usuario> usuarioExistente = repositorio.findByCorreo(usuario.getCorreo());
		if (usuarioExistente.isPresent()) {
			return repositorio.save(usuario);

		} else {
			return null;
		}
	}

	@Override
	public String  deleteUsuario(String correo) {

		Optional<Usuario> usuarioExistente = repositorio.findByCorreo(correo);
		if (usuarioExistente.isPresent()) {
			repositorio.delete(usuarioExistente.get());
			return "borrado";
		}
		return "no borrado";
	}

	@Override
	public Optional<Usuario> findByCorreoAndContrasena(String correo, String contrasena) {
		return repositorio.findByCorreoAndContrasena(correo, contrasena);
	}

	@Override
	public List<Usuario> finAll() {
		return repositorio.findAll();
	}






}