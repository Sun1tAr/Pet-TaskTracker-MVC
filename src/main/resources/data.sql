drop table if exists users;
drop table if exists tasks;

create table users (
                       id bigserial primary key,
                       username varchar(255) not null,
                       password varchar(255) not null
);

create table tasks (
                       id bigserial primary key,
                       title varchar(255) not null,
                       description varchar(255),
                       is_completed bool,
                       deadline date,
                       user_id bigint not null,

    foreign key (user_id) references users(id) on delete cascade
);



