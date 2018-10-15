create table answer (
    id varchar(32) not null primary key,
    paperid varchar(32) not null,
    userid varchar(32) not null,
    status int default 0,
    createtime datetime,
    updatetime datetime,
    index(`paperid`),
    index(`userid`)
)engine=Innodb;


create table answer_questions (
    id varchar(32) not null primary key,
    answerid varchar(32) not null,
    number int default 0,
    content text not null,
    type int(11) default 0,
    selection text not null,
    answer text not null,
    score int default -1,
    index(`answerid`)
)engine=Innodb;