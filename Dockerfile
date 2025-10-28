# Базовый образ
FROM openjdk:17-jdk-slim

# Рабочая директория
WORKDIR /app

# Копируем JAR (замени на имя твоего файла)
COPY target/*.jar pet-task_tracker-mvc.jar

# Открываем порт
EXPOSE 8081

# Команда запуска
ENTRYPOINT ["java", "-jar", "pet-task_tracker-mvc.jar"]