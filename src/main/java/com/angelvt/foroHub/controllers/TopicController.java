package com.angelvt.foroHub.controllers;

import com.angelvt.foroHub.domain.topic.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity getTopicos(@PageableDefault(size = 10, sort = "fechaCreacion", direction = Sort.Direction.DESC) Pageable paginar) {
        var topicos = topicoService.obtenerTopicos(paginar);
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<TopicoDatosRespuesta>> getTopicosCurso(@PathVariable Long cursoId) {
        var topicos = topicoService.obtenerTopicosCurso(cursoId);
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDatosCompleto> getTopico(@PathVariable Long id) {
        var topico = topicoService.obtenerTopico(id);
        return ResponseEntity.ok(topico);
    }

    @PostMapping
    public ResponseEntity<TopicoDatosRespuesta> createTopico(@RequestBody @Valid TopicoDatosRegistro datos,
                                                             UriComponentsBuilder uriComponentsBuilder) {
        var topicoNuevo = topicoService.publicarTopico(datos);
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topicoNuevo.id()).toUri();
        return ResponseEntity.created(url).body(topicoNuevo);
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
