create table user (
    id bigint generated by default as identity,
    username varchar(255) not null,
    password varchar(255) not null,
    primary key (id)
    );

-- create table user (
--     id bigint generated by default as identity,
--     username varchar(255) not null,
--     password varchar(255) not null,
--     primary key (id)
--  );

--  create table user (
--     id bigint generated by default as identity,
--     username varchar(255) not null,
--     password varchar(255) not null,
--     primary key (id)
--  );
