-- auto-generated definition
create table members
(
    id               bigint auto_increment
        primary key,
    email            varchar(255) not null,
    nick_name        varchar(255) not null,
    created_at       datetime(6)  null,
    last_modified_at datetime(6)  null,

    constraint uq_member_email
        unique (email, nick_name)
);

