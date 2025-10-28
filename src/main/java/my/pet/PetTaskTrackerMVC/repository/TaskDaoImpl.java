package my.pet.PetTaskTrackerMVC.repository;

import my.pet.PetTaskTrackerMVC.entity.Task;
import my.pet.PetTaskTrackerMVC.entity.User;
import my.pet.PetTaskTrackerMVC.interfaces.TaskDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskDaoImpl implements TaskDao {

    @PersistenceContext
    private final EntityManager entityManager;

    public TaskDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Task getTaskById(long id, User user) {
        Task task = entityManager.find(Task.class, id);
        if (task.getUser().getId().equals(user.getId())) {
            return task;
        } else {
            return null;
        }
    }

    @Override
    public List<Task> getAllTasks(User user) {
        List<Task> list = entityManager.createQuery("select t from Task t where t.user.id = :id", Task.class)
                .setParameter("id", user.getId())
                .getResultList();
        return list;
    }

    @Override
    public void deleteTaskById(long id, User user) {
        Task task = entityManager.find(Task.class, id);
        if (task.getUser().getId().equals(user.getId())) {
            entityManager.remove(task);
        }
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
