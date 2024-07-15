package com.angelvt.foroHub.controllers;

import com.angelvt.foroHub.domain.respuesta.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {
    private final RespuestaService respuestaService;

    @Autowired
    public RespuestaController(RespuestaService respuestaService) {
        this.respuestaService = respuestaService;
    }

    @GetMapping("/topico/{id}")
    public ResponseEntity<List<RespuestaDatosListaTopico>> getRespuestas(@PathVariable Long id) {
        var respuestas = respuestaService.obtenerRespuestasTopico(id);
        return ResponseEntity.ok(respuestas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaDatosCompleto> getRespuesta(@PathVariable Long id) {
        var respuesta = respuestaService.obtenerRespuesta(id);
        return ResponseEntity.ok(respuesta);
    }

    @PostMapping
    public ResponseEntity<RespuestaDatosCompleto> createRespuesta(@RequestBody @Valid RespuestaDatosRegistro datos,
                                                                  UriComponentsBuilder uriComponentsBuilder) {
        var respuesta = respuestaService.publicarRespuesta(datos);
        URI url = uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(respuesta.id()).toUri();
        return ResponseEntity.created(url).body(respuesta);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<RespuestaDatosCompleto> updateRespuesta(@PathVariable Long id, @RequestBody @Valid RespuestaDatosActualizar datosActualizar) {
        var respuesta = respuestaService.actualizarRespuesta(id, datosActualizar);
        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/solucion/{id}")
    @Transactional
    public ResponseEntity<RespuestaDatosCompleto> marcarRespuestaComoSolucion(@PathVariable Long id) {
        var respuesta = respuestaService.marcarComoSolucion(id);
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRespuesta(@PathVariable Long id) {
        respuestaService.eliminarRespuesta(id);
        return ResponseEntity.noContent().build();
    }
}
