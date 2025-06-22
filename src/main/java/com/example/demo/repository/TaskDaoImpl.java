package com.example.demo.repository;

import com.example.demo.entity.Task;
import com.example.demo.interfaces.TaskDao;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public class TaskDaoImpl implements TaskDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Task getTaskById(long id) {
        return entityManager.find(Task.class, id);
    }

    @Override
    public List<Task> getAllTasks() {
        return entityManager.createQuery("from Task", Task.class).getResultList();
    }

    @Override
    public void deleteTaskById(long id) {
    entityManager.remove(entityManager.find(Task.class, id));
    }

    @Override
    public void addTask(Task task) {
        entityManager.persist(task);
    }

    @Override
    public void updateTask(Task task, long id) {
        Task taskOld = entityManager.find(Task.class, id);
        if (taskOld != null && taskOld.getId() == task.getId()) {
            entityManager.merge(task);
        } else {
            entityManager.persist(task);
        }
    }


}
