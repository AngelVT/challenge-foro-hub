package com.angelvt.foroHub.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {
    @GetMapping
    public ResponseEntity getRespuestas() {
        return ResponseEntity.ok(new PingController.Pong("Obteniendo respuestas"));
    }

    @GetMapping("/{id}")
    public ResponseEntity getRespuesta() {
        return ResponseEntity.ok(new PingController.Pong("Obteniendo respuesta especifica"));
    }

    @PostMapping
    public ResponseEntity createRespuesta() {
        return ResponseEntity.ok(new PingController.Pong("Creando respuesta"));
    }

    @PutMapping
    public ResponseEntity updateRespuesta() {
        return ResponseEntity.ok(new PingController.Pong("Modificando respuesta"));
    }

    @DeleteMapping
    public ResponseEntity deleteRespuesta() {
        return ResponseEntity.ok(new PingController.Pong("Borrando respuesta"));
    }
}
