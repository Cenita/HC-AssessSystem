create table userMessage (
    id int not null auto_increment primary key,
    userName varchar(20) not null,
    nickName varchar(20) not null,
    trueName varchar(20) not null,
    sex int default 0,
    motto varchar(100),
    phoneNumber varchar(20),
    schoolNumber varchar(20),
    schoolName varchar(20),
    headImage int default 0,
    subjectName varchar(20),
    lasttime datetime,
    member int default 0,
    unique(`userName`)
)Engine=Innodb;

create table userPermit(
    id int not null auto_increment primary key,
    userName varchar(20) not null,
    password varchar(20) not null,
    permit int default 0,
    lasttime datetime,
    unique(`userName`)
)Engine=Innodb;

create table userDate (
    id int not null auto_increment primary key,
    userName varchar(20) not null,
    dateTime datetime,
    seconds int default 0
)Engine=Innodb;

create table guestbook(
    id int not null auto_increment primary key,
    sender varchar(20) not null,
    receiver varchar(20) not null,
    property int default 0,
    title varchar(50),
    text text,
    lasttime datetime,
    read int default 0,
    love int default 0,
    guestType int default 0
)Engine=Innodb;