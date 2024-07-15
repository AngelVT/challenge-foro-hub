package com.angelvt.foroHub.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicController {
    @GetMapping
    public ResponseEntity getTopicos() {
        return ResponseEntity.ok(new PingController.Pong("Obteniendo topicos"));
    }

    @GetMapping("/{id}")
    public ResponseEntity getTopico(@PathVariable Long id) {
        return ResponseEntity.ok(new PingController.Pong("Obteniendo topico " + id));
    }

    @PostMapping
    public ResponseEntity createTopico() {
        return ResponseEntity.ok(new PingController.Pong("Creando topico"));
    }

    @PutMapping
    public ResponseEntity updateTopico() {
        return ResponseEntity.ok(new PingController.Pong("Modificando topico"));
    }

    @DeleteMapping
    public ResponseEntity deleteTopico() {
        return ResponseEntity.ok(new PingController.Pong("Borrando topico"));
    }
}
