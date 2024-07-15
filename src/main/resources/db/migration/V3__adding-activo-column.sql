ALTER TABLE topicos ADD activo tinyint;
UPDATE topicos SET activo = 1;

ALTER TABLE respuestas ADD activo tinyint;
UPDATE respuestas SET activo = 1;