package com.example.foroHub.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
public class PingController {
    @GetMapping
    public ResponseEntity<Pong> sayPong() {
        var response = new Pong("pong");
        return ResponseEntity.ok(response);
    }

    public record Pong(String msg) {
    }

}
