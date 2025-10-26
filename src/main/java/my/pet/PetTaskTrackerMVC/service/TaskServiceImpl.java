package my.pet.PetTaskTrackerMVC.service;

import my.pet.PetTaskTrackerMVC.entity.Task;
import my.pet.PetTaskTrackerMVC.interfaces.TaskDao;
import my.pet.PetTaskTrackerMVC.interfaces.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDao taskDao;

    @Override
    public Task getTaskById(long id) {
        return taskDao.getTaskById(id);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskDao.getAllTasks();
    }

    @Override
    @Transactional
    public void deleteTaskById(long id) {
        taskDao.deleteTaskById(id);
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
