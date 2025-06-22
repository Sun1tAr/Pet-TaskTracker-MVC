package com.example.demo.controller;

import com.example.demo.entity.Task;
import com.example.demo.service.TaskServiceImpl;
import com.example.demo.util.DateUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping({"/tasks", "/tasks/"})
public class TaskController {

    @Autowired
    TaskServiceImpl taskService;

//  Список всех задач
    @GetMapping
    public String getAllTasks(Model model, boolean showAll) {
        List allTasks = taskService.getAllTasks();
        model.addAttribute("listOfTasks", allTasks);
        model.addAttribute("dateUtils", new DateUtils());
        model.addAttribute("showAll", showAll);
        return "tasks";
    }

    //    Удаление задачи по ID
    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable("id") int taskId) {
    taskService.deleteTaskById(taskId);
    return "redirect:/tasks";
}

    //    Изменение статуса задачи
    @PutMapping("/{id}/toggle")
    public String toggleStatus(@PathVariable("id") int taskId) {
        Task task = taskService.getTaskById(taskId);
        taskService.toggleTaskStatus(task);
        return "redirect:/tasks";
    }

    //    Запрос для добавления сущности в модель и перевод в новую вью
    @GetMapping("/add")
    public String addTask(Model model) {
        model.addAttribute("currentTask", new Task());

        StringBuilder path = new StringBuilder("/tasks");
        path.append("/add");
        model.addAttribute("variousPath", path.toString());

        return "task";
    }

    //    Запрос на добавление сущности в систему
    @PostMapping("/add")
    public String addTask(@Valid @ModelAttribute("currentTask") Task task, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "task";
        } else {
            taskService.addTask(task);
            return "redirect:/tasks";
        }
    }

    //    Запрос на добавление изменяемой задачи в модель
    @GetMapping("/{id}")
    public String changeTask(Model model, @PathVariable("id") long taskId) {
        Task task = taskService.getTaskById(taskId);
        model.addAttribute("currentTask", task);

        StringBuilder path = new StringBuilder("/tasks/");
        path.append(taskId);

        model.addAttribute("variousPath", path.toString());

        return "task";
    }

    //    Запрос на изменение задачи
    @PutMapping("/{id}")
    public String changeTask(@Valid @ModelAttribute("currentTask") Task task, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "task";
        } else {
            taskService.updateTask(task, task.getId());
            return "redirect:/tasks";
        }
    }
}
