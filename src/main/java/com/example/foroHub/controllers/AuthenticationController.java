package com.example.foroHub.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @PostMapping("/login")
    public ResponseEntity autenticarUsuario() {
        return ResponseEntity.ok(new PingController.Pong("Autenticando usuario"));
    }

    @PostMapping("/signup")
    public ResponseEntity registrandoUsuario() {
        return ResponseEntity.ok(new PingController.Pong("Registrando usuario usuario"));
    }

}
