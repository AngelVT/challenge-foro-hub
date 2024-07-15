package com.angelvt.foroHub.controllers;

import com.angelvt.foroHub.domain.topic.TopicoDatosRegistro;
import com.angelvt.foroHub.domain.topic.TopicoService;
import com.angelvt.foroHub.domain.topic.TopicoDatosRespuesta;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicController {
    private final TopicoService topicoService;

    @Autowired
    public TopicController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }

    @GetMapping
    public ResponseEntity<List<TopicoDatosRespuesta>> getTopicos() {
        var topicos = topicoService.obtenerTopicos();
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity getTopico(@PathVariable Long id) {
        var topico = topicoService.obtenerTopico(id);
        return ResponseEntity.ok(topico);
    }

    @PostMapping
    public ResponseEntity<TopicoDatosRespuesta> createTopico(@RequestBody @Valid TopicoDatosRegistro datos) {
        var topicoNuevo = topicoService.publicarTopico(datos);
        return ResponseEntity.ok(topicoNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateTopico() {
        return ResponseEntity.ok(new PingController.Pong("Modificando topico"));
    }

    @PutMapping("/cerrar/{id}")
    public ResponseEntity cerrarTopico() {
        return ResponseEntity.ok(new PingController.Pong("Cerrando topico"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTopico() {
        return ResponseEntity.ok(new PingController.Pong("Borrando topico"));
    }
}
