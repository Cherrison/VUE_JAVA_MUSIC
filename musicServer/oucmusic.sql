create table if not exists music
(
    id       int(11) unsigned auto_increment
        primary key,
    md5value varchar(50) default '' not null,
    name     varchar(100)           null,
    times    int(10)     default 0  null
)
    charset = utf8;

create table if not exists musicsheet
(
    id          int(11) unsigned auto_increment
        primary key,
    uuid        varchar(50) default '' not null,
    name        varchar(50)            null,
    creatorId   varchar(50)            null,
    creator     varchar(50)            null,
    dateCreated varchar(50)            null,
    picture     varchar(100)           null
)
    charset = utf8;

create table if not exists musicsheet_music
(
    id           int(11) unsigned auto_increment
        primary key,
    musicsheetId int(11) unsigned not null,
    musicId      int(11) unsigned not null,
    constraint FK_MUSIC
        foreign key (musicId) references music (id),
    constraint FK_MUSICSHEET
        foreign key (musicsheetId) references musicsheet (id)
)
    charset = utf8;

create table if not exists user
(
    id           int auto_increment
        primary key,
    name         varchar(30)  not null,
    passMd5value varchar(100) not null,
    constraint user_name_uindex
        unique (name)
);

create table if not exists user_music_like
(
    id       int auto_increment
        primary key,
    username varchar(30)                 null,
    md5value varchar(100)                null,
    isLike   varchar(10) default 'false' null
);


