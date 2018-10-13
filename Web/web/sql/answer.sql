create table answer (
    id varchar(32) not null primary key,
    paperid varchar(32) not null,
    userid varchar(32) not null,
    status int default 0,
    createtime datetime,
    updatetime datetime,
    index(`paperid`),
    index(`userid`)
)engine=Innodb default character utf8;


create table answer_questions (
    id varchar(32) not null primary key,
    questionid varchar(32) not null,
    answer text,
    score int default 0,
    index(`questionid`)
)engine=Innodb default character utf8;