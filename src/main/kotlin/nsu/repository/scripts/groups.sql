create table groups
(
    id   integer not null
        constraint groups_pk
            primary key,
    name varchar
);

alter table groups
    owner to admin;

