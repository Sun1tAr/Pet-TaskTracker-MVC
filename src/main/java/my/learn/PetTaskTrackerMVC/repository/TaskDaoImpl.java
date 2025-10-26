package my.learn.PetTaskTrackerMVC.repository;

import my.learn.PetTaskTrackerMVC.entity.Task;
import my.learn.PetTaskTrackerMVC.interfaces.TaskDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

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
