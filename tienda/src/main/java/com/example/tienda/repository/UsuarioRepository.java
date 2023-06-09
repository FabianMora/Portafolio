package com.example.tienda.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.tienda.model.Usuario;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
	@Query("{correo:'?0',contrasena:'?1'}")
	public Optional<Usuario> findByCorreoAndContrasena(String correo,String contrasena);
	
	@Query("{correo:'?0'}")
	public Optional<Usuario> findByCorreo(String correo);
}