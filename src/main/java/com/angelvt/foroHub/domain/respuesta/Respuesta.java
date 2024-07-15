package com.angelvt.foroHub.domain.respuesta;

import com.angelvt.foroHub.domain.topic.Topico;
import com.angelvt.foroHub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor")
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico")
    private Topico topico;

    private String mensaje;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    private Boolean solucion;

    private Boolean activo;

    public Respuesta(Usuario autor, Topico topico, String mensaje) {
        this.autor = autor;
        this.topico = topico;
        this.mensaje = mensaje;
        this.fechaCreacion = LocalDateTime.now();
        this.solucion = false;
        this.activo = true;
    }

    public void actualizar(String mensaje) {
        this.mensaje = mensaje;
    }

    public void marcarSolucion(boolean b) {
        this.solucion = true;
    }

    public void eliminar() {
        this.activo = false;
    }
}
