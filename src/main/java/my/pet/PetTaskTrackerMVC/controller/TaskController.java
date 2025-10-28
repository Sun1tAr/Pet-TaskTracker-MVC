package my.pet.PetTaskTrackerMVC.controller;

import my.pet.PetTaskTrackerMVC.entity.Task;
import my.pet.PetTaskTrackerMVC.entity.User;
import my.pet.PetTaskTrackerMVC.interfaces.UserService;
import my.pet.PetTaskTrackerMVC.service.TaskServiceImpl;
import my.pet.PetTaskTrackerMVC.util.DateUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping({"/tasks", "/tasks/"})
public class TaskController {


    private final TaskServiceImpl taskService;
    private final UserService userService;

    public TaskController(TaskServiceImpl taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }


    //  Список всех задач
    @GetMapping
    public String getAllTasks(Model model, boolean showAll, @AuthenticationPrincipal UserDetails details) {

        List<Task> allTasks;
        Optional<User> user = userService.findByUserName(details.getUsername());
        if (user.isPresent()) {
            allTasks = taskService.getAllTasks(user.get());
        } else {
            allTasks = new ArrayList<>();
        }

        model.addAttribute("listOfTasks", allTasks);
        model.addAttribute("dateUtils", new DateUtils());
        model.addAttribute("showAll", showAll);
        return "tasks.html";
    }

    //    Удаление задачи по ID
    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable("id") int taskId, @AuthenticationPrincipal UserDetails details) {
        Optional<User> userOpt = userService.findByUserName(details.getUsername());

        userOpt.ifPresent(user -> taskService.deleteTaskById(taskId, user));

    return "redirect:/tasks";
}

    //    Изменение статуса задачи
    @PutMapping("/{id}/toggle")
    public String toggleStatus(@PathVariable("id") int taskId, @AuthenticationPrincipal UserDetails details) {
        Task task;
        Optional<User> userOpt = userService.findByUserName(details.getUsername());
        if (userOpt.isPresent()) {
            task = taskService.getTaskById(taskId, userOpt.get());
            taskService.toggleTaskStatus(task);
        }
        return "redirect:/tasks";
    }

    //    Запрос для добавления сущности в модель и перевод в новую вью
    @GetMapping("/add")
    public String addTask(Model model, @AuthenticationPrincipal UserDetails details) {
        Optional<User> userOpt = userService.findByUserName(details.getUsername());
        if (userOpt.isPresent()) {
            Task t = new Task();
            t.setUser(userOpt.get());
            model.addAttribute("currentTask", t);

            StringBuilder path = new StringBuilder("/tasks");
            path.append("/add");
            model.addAttribute("variousPath", path.toString());

            return "task.html";
        } else {
            return "redirect:/tasks";
        }

    }

    //    Запрос на добавление сущности в систему
    /*
    * В идеале тут тянуть таску с юзером из кэша, ноооо
    * как это не печально, для первого public проекта воспользуемся хардкодингом
    *  (aka Redis пока не изучен, хех)
    * */
    @PostMapping("/add")
    public String addTask(@Valid @ModelAttribute("currentTask") Task task, BindingResult bindingResult, @AuthenticationPrincipal UserDetails details) {
        if (bindingResult.hasErrors()) {
            return "task.html";
        } else {
            Optional<User> userOpt = userService.findByUserName(details.getUsername());
            if (userOpt.isPresent()) {
                task.setUser(userOpt.get());
                taskService.addTask(task);
            }
            return "redirect:/tasks";
        }
    }

    //   Запрос на добавление изменяемой задачи в модель
    @GetMapping("/{id}")
    public String changeTask(Model model, @PathVariable("id") long taskId, @AuthenticationPrincipal UserDetails details) {
        Task task = null;
        Optional<User> userOpt = userService.findByUserName(details.getUsername());
        if (userOpt.isPresent()) {
            task = taskService.getTaskById(taskId, userOpt.get());
        }

        if (task != null) {
            model.addAttribute("currentTask", task);

            StringBuilder path = new StringBuilder("/tasks/");
            path.append(taskId);

            model.addAttribute("variousPath", path.toString());

            return "task.html";
        } else {
            return "redirect:/tasks";
        }
    }

    //    Запрос на изменение задачи
    @PutMapping("/{id}")
    public String changeTask(@Valid @ModelAttribute("currentTask") Task task, BindingResult bindingResult, @AuthenticationPrincipal UserDetails details) {
        if (bindingResult.hasErrors()) {
            return "task";
        } else {
            Optional<User> userOpt = userService.findByUserName(details.getUsername());
            if (userOpt.isPresent()) {
                task.setUser(userOpt.get());
                taskService.updateTask(task, task.getId());
            }
            return "redirect:/tasks";
        }
    }
}
