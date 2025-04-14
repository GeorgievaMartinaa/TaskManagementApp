package com.example.taskmanagementapp.web;

import com.example.taskmanagementapp.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String listAllTasks(Model model){
        model.addAttribute("taskList", taskService.getAllTasks());

        return "allTasksPage";
    }
}
