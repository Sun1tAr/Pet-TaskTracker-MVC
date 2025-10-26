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