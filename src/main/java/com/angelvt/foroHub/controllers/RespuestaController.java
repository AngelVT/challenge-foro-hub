package com.angelvt.foroHub.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {
    @GetMapping("/topico/{id}")
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

    @PutMapping("/{id}")
    public ResponseEntity updateRespuesta() {
        return ResponseEntity.ok(new PingController.Pong("Modificando respuesta"));
    }

    @PutMapping("/solucion/{id}")
    public ResponseEntity marcarRespuestaComoSolucion() {
        return ResponseEntity.ok(new PingController.Pong("Marcando como solucion"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRespuesta() {
        return ResponseEntity.ok(new PingController.Pong("Borrando respuesta"));
    }
}
