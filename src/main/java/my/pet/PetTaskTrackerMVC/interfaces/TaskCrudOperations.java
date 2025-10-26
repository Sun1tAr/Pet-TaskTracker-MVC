package my.pet.PetTaskTrackerMVC.interfaces;

import my.pet.PetTaskTrackerMVC.entity.Task;

import java.util.List;

public interface TaskCrudOperations {

    public Task getTaskById(long id);

    public List<Task> getAllTasks();

    public void deleteTaskById(long id);

    public void addTask(Task task);

    public void updateTask(Task task, long id);


}
