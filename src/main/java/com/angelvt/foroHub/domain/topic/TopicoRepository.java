package com.angelvt.foroHub.domain.topic;

import com.angelvt.foroHub.domain.curso.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    List<Topico> findByCursoAndActivoTrue(Curso curso);

    boolean existsByIdAndActivoTrue(Long id);

    List<Topico> findAllByActivoTrue();
}
