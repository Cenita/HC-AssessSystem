create table paper_questions (
    id varchar(32) not null primary key,
    questionid varchar(32) not null,
    paperid varchar(32) not null,
    unique(`questionid`),
    unique(`paperid`)
)Engine=Innodb;