create table filmes (
    id bigint not null auto_increment,
    titulo varchar(255) not null,
    sinopse varchar(255),
    ano_lancamento int,
    primary key(id)
);