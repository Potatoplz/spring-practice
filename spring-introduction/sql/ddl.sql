create table member
(
    id bigint generated by default as identity, // DB들어오면 자동으로 id세팅
    name varchar(255),
    primary key (id)
    );

insert into member(name) values('spring')