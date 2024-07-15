package com.angelvt.foroHub.controllers;

import com.angelvt.foroHub.domain.topic.*;
import jakarta.transaction.Transactional;
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

    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<TopicoDatosRespuesta>> getTopicos(@PathVariable Long cursoId) {
        var topicos = topicoService.obtenerTopicos(cursoId);
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDatosCompleto> getTopico(@PathVariable Long id) {
        var topico = topicoService.obtenerTopico(id);
        return ResponseEntity.ok(topico);
    }

    @PostMapping
    public ResponseEntity<TopicoDatosRespuesta> createTopico(@RequestBody @Valid TopicoDatosRegistro datos) {
        var topicoNuevo = topicoService.publicarTopico(datos);
        return ResponseEntity.ok(topicoNuevo);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDatosRespuesta> updateTopico(@PathVariable Long id, @RequestBody @Valid TopicoDatosActualizar datos) {
        var topico = topicoService.actualizarTopico(id ,datos);
        return ResponseEntity.ok(topico);
    }

    @PutMapping("/cerrar/{id}")
    @Transactional
    public ResponseEntity<TopicoDatosRespuesta> cerrarTopico(@PathVariable Long id, @RequestBody @Valid TopicoDatosActualizar datos) {
        var topico = topicoService.cerrarTopico(id ,datos);
        return ResponseEntity.ok(topico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteTopico(@PathVariable Long id) {
        topicoService.borrarTopico(id);
        return ResponseEntity.noContent().build();
    }
}
