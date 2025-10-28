package my.pet.PetTaskTrackerMVC.interfaces;

import my.pet.PetTaskTrackerMVC.entity.Task;
import my.pet.PetTaskTrackerMVC.entity.User;

import java.util.List;

public interface TaskCrudOperations {

    public Task getTaskById(long id, User user);

    public List<Task> getAllTasks(User user);

    public void deleteTaskById(long id, User user);

    public void addTask(Task task);

    public void updateTask(Task task, long id);


}
