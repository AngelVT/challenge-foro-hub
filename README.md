<h1 align="center"> Challenge ForoHub </h1>

![Badge en Finalizado](https://img.shields.io/badge/STATUS-COMPLETADO-green)
![Badge Java](https://img.shields.io/badge/VERSION_JAVA-17-blue)
![Badge Java](https://img.shields.io/badge/VERSION_PROYECTO-1.0-orange)

Este proyecto es una aplicación de gestión de foros diseñada para cursos educativos. Proporciona funcionalidades completas para manejar tópicos y respuestas de manera eficiente, asegurando la integridad y la coherencia de los datos a través de diversas reglas de negocio.

## :hammer:Funcionalidades del proyecto

- `Operaciones CRUD para topicos`
  - No se pueden crear topicos con el mismo titulo o el mismo mensaje que un topico existente.
  - Un topico marcado como CERRADO, CERRADO_RESUELTO o RESUELTO no puede ser modificado.
  - Un topico solo puede ser marcado como resuelto si una de sus respuestas se marca como solucion.
- `Operaciones CRUD para respuestas a topicos`
  - Solo se pueden crear respuestas a topicos que se encuentren en estado ABIERTO.
  - Las respuestas solo se pueden modificar mientras el topico correspondiente este ABIERTO
  - Despues de marcar una respuesta como solucion no se podran hacer mas respuestas a topico correspondiente.
- `Estados de topico`
  - ABIERTO: El topico se puede modificar tanto titulo como mensaje y se pueden hacer y modificar respuestas a dicho topico.
  - CERRADO: El topico fue cerrado sin solucion y bloquerara todas las modificaciones a respuestas y al topico mismo.
  - CERRADO_RESUELTO:El topico fue cerrado con una solucion externa al foro y bloquerara todas las modificaciones a respuestas y al topico mismo.
  - RESUELTO: El topico fur cerrado con una solucion proveniente de una respuesta del foro, bloqueara tofdas las modificaciones al topico y sus respuestas
- `Los topicos se agrupan por curso`
- `Las respuestas se agrupan por topico`

## Pre-requisitos 📋

- La base de datos debe ser creada previamente, las tablas se generan durante la primer ejecucion
- EL proyecto toma variables de entorno para realizar la conexion a la base de datos las variables deben ser las siguientes
  - `LIT_DB_HOST`='nombre del host'
  - `LIT_DB_PORT`='puerto de la base de datos'
  - `LIT_DB_NAME`='nombre de base de datos'
  - `LIT_DB_USER`='usuario de base de datos'
  - `LIT_DB_PASSWORD`='contraseña de base de datos'
  - `LIT_JWT_SECRET`='secreto para firmar JWT'
 
## Tecnologias usadas 🛠️

* Java
* Spring
* MySQL
* JWT
