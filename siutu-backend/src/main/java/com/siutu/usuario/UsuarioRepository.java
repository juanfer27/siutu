package com.siutu.usuario;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Optional<Usuario> findByCorreoElectronico(String correoElectronico);
}