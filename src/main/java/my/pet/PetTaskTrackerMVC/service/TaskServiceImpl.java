package my.pet.PetTaskTrackerMVC.service;

import my.pet.PetTaskTrackerMVC.entity.Task;
import my.pet.PetTaskTrackerMVC.entity.User;
import my.pet.PetTaskTrackerMVC.interfaces.TaskDao;
import my.pet.PetTaskTrackerMVC.interfaces.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDao taskDao;

    @Override
    public Task getTaskById(long id, User user) {
        return taskDao.getTaskById(id, user);
    }

    @Override
    public List<Task> getAllTasks(User user) {
        return taskDao.getAllTasks(user);
    }

    @Override
    @Transactional
    public void deleteTaskById(long id, User user) {
        taskDao.deleteTaskById(id, user);
    }

    @Transactional
    @Override
    public void addTask(Task task) {
        taskDao.addTask(task);
    }

    @Transactional
    @Override
    public void updateTask(Task task, long id) {
        taskDao.updateTask(task, id);
    }

    @Transactional
    @Override
    public void toggleTaskStatus(Task task) {
        task.toggleIsDone();
    }
}
