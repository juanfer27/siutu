package com.siutu.usuario;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:3000") // Permitir solicitudes desde el frontend
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        logger.info("Datos del usuario a registrar: " + usuario);
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        logger.info("Usuario registrado con éxito: " + usuarioGuardado);
        return usuarioGuardado;
    }


    @PostMapping("/login")
    public Usuario login(@RequestBody Usuario usuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByCorreoElectronico(usuario.getCorreoElectronico());
        if (usuarioOptional.isPresent() && passwordEncoder.matches(usuario.getContrasena(), usuarioOptional.get().getContrasena())) {
            return usuarioOptional.get();
        } else {
            throw new RuntimeException("Credenciales inválidas");
        }
    }
}