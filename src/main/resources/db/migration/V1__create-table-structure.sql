create table usuarios (
    id bigint not null auto_increment,
    nombre varchar(100) not null,
    email varchar(100) not null unique,
    password varchar(300) not null,
    primary key (id)
);

create table cursos (
    id bigint not null auto_increment,
    nombre varchar(100) not null,
    categoria varchar(100) not null,
    primary key (id)
);

create table topicos (
    id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensaje varchar(500) not null,
    fecha_creacion datetime not null,
    status varchar(100) not null,
    autor bigint not null,
    curso bigint not null,
    primary key (id),
    foreign key (autor) references usuarios(id),
    foreign key (curso) references cursos(id)
);

create table respuestas (
    id bigint not null auto_increment,
    autor bigint not null,
    topico bigint not null,
    mensaje varchar(500) not null,
    fecha_creacion datetime not null,
    solucion tinyint default 0,
    primary key (id),
    foreign key (autor) references usuarios(id),
    foreign key (topico) references topicos(id)
);

-- Create indexes
create index idx_autor_topicos on topicos(autor);
create index idx_curso_topicos on topicos(curso);
create index idx_autor_respuestas on respuestas(autor);
create index idx_topico_respuestas on respuestas(topico);