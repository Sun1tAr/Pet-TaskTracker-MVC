# 🚀 Task Tracker - Spring Boot MVC Приложение

Современное веб-приложение для управления персональными задачами с полноценной системой аутентификации и изоляцией данных между пользователями.

## ✨ Ключевые возможности

- **🔐 Безопасная аутентификация** - Spring Security с кодированием паролей BCrypt
- **👥 Изоляция данных** - Каждый пользователь видит только свои задачи
- **📱 Адаптивный дизайн** - Современные Thymeleaf шаблоны с CSS3
- **⏰ Умные уведомления** - Визуализация сроков выполнения задач
- **🎯 Полный CRUD** - Создание, чтение, обновление, удаление задач
- **🔒 Безопасность на уровне данных** - Проверка прав доступа к задачам

## 🛠️ Технологический стек

**Backend:**
- Spring Boot 3.5.3
- Spring Security 6.5.1
- Spring Data JPA
- Hibernate 6.6.18
- PostgreSQL / H2 (для разработки)
- Maven

**Frontend:**
- Thymeleaf 3.1.3
- CSS3 с кастомными свойствами
- HTML5
- Responsive design

**Безопасность:**
- BCrypt password encoding
- CSRF protection
- Session management
- Remember-me функциональность

## 🏗️ Архитектурные улучшения

### Мультитенантная архитектура
```java
// Изоляция данных на уровне репозитория
public List<Task> getAllTasks(User user) {
    return entityManager.createQuery("select t from Task t where t.user.id = :id", Task.class)
            .setParameter("id", user.getId())
            .getResultList();
}
```
### Безопасность на уровне бизнес-логики
```java

// Проверка прав доступа к задаче
public Task getTaskById(long id, User user) {
    Task task = entityManager.find(Task.class, id);
    if (task.getUser().getId().equals(user.getId())) {
        return task;
    } else {
        return null; // Доступ запрещен
    }
}
```
## 🚀 Быстрый старт
### Требования:

- Java 17+
- PostgreSQL 13+
- Maven 3.6+

### Запуск:

#### Клонировать репозиторий:

```bash

git clone https://github.com/your-username/task-tracker.git
cd task-tracker
```
#### Настроить базу данных:

```sql
    CREATE DATABASE task_tracker;

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



```


#### Настроить подключение к БД в application.properties

#### Запустить приложение:

```bash

mvn spring-boot:run
```
Открыть в браузере: http://localhost:8081

## 📁 Структура проекта
```text

src/
├── main/
│   ├── java/
│   │   └── my/pet/PetTaskTrackerMVC/
│   │       ├── config/          # Конфигурация Spring Security
│   │       ├── controller/      # MVC контроллеры
│   │       ├── entity/          # JPA сущности (User, Task)
│   │       ├── repository/      # Data Access Layer с изоляцией
│   │       ├── service/         # Business Logic Layer
│   │       └── util/           # Вспомогательные классы
│   └── resources/
│       ├── templates/          # Thymeleaf шаблоны
│       └── data.sql           # SQL миграции
```


## 🚀 Особенности реализации
Безопасность

- Изоляция на уровне репозитория - все запросы фильтруются по user_id

- Проверка прав доступа - перед каждой операцией с задачей

- Spring Security Integration - @AuthenticationPrincipal в контроллерах

### Производительность

- Транзакционность - @Transactional на сервисном слое

- Оптимизированные запросы - фильтрация на стороне БД

- Lazy loading - настройки FetchType для связей

### Пользовательский опыт

- Валидация форм - проверка данных на стороне клиента и сервера

- Умные уведомления - цветовая индикация сроков

- Адаптивный дизайн - работа на мобильных устройствах



## 📝 Лицензия

MIT License - можно свободно использовать для обучения и коммерческих проектов.

