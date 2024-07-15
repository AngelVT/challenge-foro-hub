package com.angelvt.foroHub.controllers;

import com.angelvt.foroHub.domain.usuario.UsuarioDatosAutenticacion;
import com.angelvt.foroHub.domain.usuario.UsuarioDatosRegistro;
import com.angelvt.foroHub.domain.usuario.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final UsuarioService usuarioService;

    @Autowired
    public AuthenticationController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity autenticarUsuario(@RequestBody @Valid UsuarioDatosAutenticacion datos) {
        var token = usuarioService.autenticarUsuario(datos);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/signup")
    public ResponseEntity registrandoUsuario(@RequestBody @Valid UsuarioDatosRegistro datos) {
        var token = usuarioService.registrarUsuario(datos);
        return ResponseEntity.ok(token);
    }

}
