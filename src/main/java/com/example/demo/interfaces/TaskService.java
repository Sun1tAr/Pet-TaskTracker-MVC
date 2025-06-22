package com.example.demo.interfaces;

import com.example.demo.entity.Task;

public interface TaskService extends TaskCrudOperations{

    public void toggleTaskStatus(Task task);

}
