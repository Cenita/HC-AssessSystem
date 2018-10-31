create table carousel(
    id varchar(32) not null primary key,
    url varchar(255) not null,
    number varchar(32) not null,
    title varchar(100) not null,
    content text,
    unique(`number`),
    index(`title`)
)engine=Innodb;