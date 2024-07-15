package com.angelvt.foroHub.domain.respuesta;

import com.angelvt.foroHub.domain.topic.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Topico> {
}
