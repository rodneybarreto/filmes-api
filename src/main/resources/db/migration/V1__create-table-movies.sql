create table movies (
    id bigint not null auto_increment,
    title varchar(255) not null,
    synopsis varchar(255),
    release_year int,
    primary key(id)
);