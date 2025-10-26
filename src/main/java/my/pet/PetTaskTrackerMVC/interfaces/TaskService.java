package my.pet.PetTaskTrackerMVC.interfaces;

import my.pet.PetTaskTrackerMVC.entity.Task;

public interface TaskService extends TaskCrudOperations{

    public void toggleTaskStatus(Task task);

}
