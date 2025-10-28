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

CREATE TABLE users_tasks
(
    user_id  BIGINT NOT NULL,
    tasks_id BIGINT NOT NULL
);

ALTER TABLE users_tasks
    ADD CONSTRAINT uc_users_tasks_tasks UNIQUE (tasks_id);

ALTER TABLE users
    ADD CONSTRAINT uc_users_username UNIQUE (username);

ALTER TABLE users_tasks
    ADD CONSTRAINT fk_usetas_on_task FOREIGN KEY (tasks_id) REFERENCES tasks (id);

ALTER TABLE users_tasks
    ADD CONSTRAINT fk_usetas_on_user FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE users
    ALTER COLUMN password DROP NOT NULL;

ALTER TABLE tasks
    ALTER COLUMN title DROP NOT NULL;

ALTER TABLE tasks
    ALTER COLUMN user_id DROP NOT NULL;

ALTER TABLE users
    ALTER COLUMN username DROP NOT NULL;


