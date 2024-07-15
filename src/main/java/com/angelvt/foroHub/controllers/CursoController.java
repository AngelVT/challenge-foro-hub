package com.angelvt.foroHub.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    @GetMapping
    public ResponseEntity getCursos() {
        return ResponseEntity.ok(new PingController.Pong("Obteniendo cursos"));
    }

    @GetMapping("/{id}")
    public ResponseEntity getCurso(@PathVariable Long id) {
        return ResponseEntity.ok(new PingController.Pong("Obteniendo curso " + id));
    }

    @PostMapping
    public ResponseEntity createCursos() {
        return ResponseEntity.ok(new PingController.Pong("Creando curso"));
    }

    @PutMapping
    public ResponseEntity updateCurso() {
        return ResponseEntity.ok(new PingController.Pong("Modificando curso"));
    }

    @DeleteMapping
    public ResponseEntity deleteCurso() {
        return ResponseEntity.ok(new PingController.Pong("Borrando curso"));
    }
}
