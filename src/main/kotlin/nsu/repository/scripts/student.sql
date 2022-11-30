create table students
(
    id         integer not null
        constraint students_pk
            primary key,
    first_name varchar,
    last_name  varchar,
    group_id   integer not null
        constraint students_groups_null_fk
            references groups (id)
);

create index students_group_id_index
    on students (group_id);
