create table paper (
    id varchar(32) not null primary key,
    title varchar(32) not null,
    number varchar(32) not null,
    direction varchar(32) not null,
    grade int default 0,
    permit int default 0,
    createtime datetime,
    updatetime datetime,
    starttime datetime,
    endtime datetime,
    unique(`title`),
    unique(`number`)
)Engine=Innodb;