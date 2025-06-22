drop table if exists tasks;

create table tasks (
                       id bigserial primary key,
                       title varchar(255) not null,
                       description varchar(255),
                       is_completed bool,
                       deadline date
);

select * from tasks;