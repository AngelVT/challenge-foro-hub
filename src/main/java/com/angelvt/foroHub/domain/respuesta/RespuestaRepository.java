package com.angelvt.foroHub.domain.respuesta;

import com.angelvt.foroHub.domain.topic.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
    List<Respuesta> findByTopicoAndActivoTrue(Topico topico);

    boolean existsByIdAndActivoTrue(Long id);
}
