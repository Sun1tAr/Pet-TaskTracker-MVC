package com.example.demo.controller;

import com.example.demo.entity.Task;
import com.example.demo.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rest/tasks")
public class TaskRestController {

    @Autowired
    TaskServiceImpl taskService;

    @GetMapping("/getAll")
    public List getAllTasks() {
        List<Task> allTasks = taskService.getAllTasks();
        return allTasks;
    }

    @GetMapping("/getById")
    public Task getTaskById(@RequestParam("id") Long id) {
        Task task = taskService.getTaskById(id);
        return task;
    }

    @GetMapping("/putTests")
    public void putTests() {
        Task task;
        LocalDate d;
        for (short i = 1; i < 11; i++) {
            task = new Task();
            task.setTitle("Task №" + i);
            task.setDescription("Description for task: " + task.getTitle());
            d = LocalDate.now();
            task.setDueDate(d);
            taskService.addTask(task);
        }
    }

    @PostMapping
    public void addTask(@RequestBody Task task) {
        taskService.addTask(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable("id") long taskId) {
        taskService.deleteTaskById(taskId);
    }

    @PutMapping("/{id}/toggle")
    public Task toggleStatus(@PathVariable("id") long taskId) {
        Task task = taskService.getTaskById(taskId);
        taskService.toggleTaskStatus(task);
        return task;
    }

}
