package com.angelvt.foroHub.domain.topic;

import com.angelvt.foroHub.domain.curso.Curso;
import com.angelvt.foroHub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Setter
    @Enumerated(EnumType.STRING)
    private Estado status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor")
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso")
    private Curso curso;

    private Boolean activo;

    public void actualizar(TopicoDatosActualizar datos) {
        if (datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }

        if (datos.titulo() != null) {
            this.titulo = datos.titulo();
        }
    }

    public void eliminar() {
        this.activo = false;
    }
}
