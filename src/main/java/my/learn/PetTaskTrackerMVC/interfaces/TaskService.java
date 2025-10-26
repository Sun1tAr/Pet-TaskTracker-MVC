package my.learn.PetTaskTrackerMVC.interfaces;

import my.learn.PetTaskTrackerMVC.entity.Task;

public interface TaskService extends TaskCrudOperations{

    public void toggleTaskStatus(Task task);

}
