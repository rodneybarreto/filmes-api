create table customers (
    id bigint not null auto_increment,
    name varchar(255) not null,
    email varchar(255) not null,
    pix_key varchar(255),
    primary key(id),
    unique(email)
);