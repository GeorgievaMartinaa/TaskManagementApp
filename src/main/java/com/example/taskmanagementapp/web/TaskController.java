package com.example.taskmanagementapp.web;

import com.example.taskmanagementapp.model.Card;
import com.example.taskmanagementapp.model.DTO.CardDTO;
import com.example.taskmanagementapp.model.DTO.TaskDTO;
import com.example.taskmanagementapp.model.Task;
import com.example.taskmanagementapp.service.CardService;
import com.example.taskmanagementapp.service.TaskService;
import com.example.taskmanagementapp.service.mapper.CardMapper;
import com.example.taskmanagementapp.service.mapper.TaskMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private TaskService taskService;
    private CardService cardService;
    private TaskMapper taskMapper;
    private CardMapper cardMapper;

    public TaskController(TaskService taskService, CardService cardService, TaskMapper taskMapper, CardMapper cardMapper) {
        this.taskService = taskService;
        this.cardService = cardService;
        this.taskMapper = taskMapper;
        this.cardMapper = cardMapper;
    }

    @GetMapping
    public String listAllTasks(Model model){

        List<Task> tasks = taskService.getAllTasks();

        List<TaskDTO> taskDTOS = tasks.stream().map(task -> taskMapper.taskToDto(task)).collect(Collectors.toList());

        model.addAttribute("taskList", taskDTOS);

        return "allTasksPage";
    }

    @GetMapping("/new")
    public String newTask(Model model){

        model.addAttribute("task", new TaskDTO());

        List<Card> cards = cardService.getAllCards();

        List<CardDTO> cardDTOS = cards.stream().map(card -> cardMapper.toCardDTO(card)).collect(Collectors.toList());

        model.addAttribute("cards", cardDTOS);

        return "createNewTask";
    }

    @PostMapping("/process")
    public String processTask(@ModelAttribute("task") TaskDTO taskDto){

        Card card = cardService.findCardById(taskDto.getCardId());

        Task task = taskMapper.dtoToTask(taskDto, card);

        task.setDone(false);

        taskService.save(task);

        return "redirect:/home";
    }

    @PatchMapping("/{id}/done")
    @ResponseBody
    public ResponseEntity<String> updateTaskDone (@PathVariable int id, @RequestBody Map<String,Boolean> payload){

        Task task = taskService.findTaskById(id);

        if(task == null)
            return ResponseEntity.notFound().build();

        Boolean done = payload.get("done");
        task.setDone(done);

        taskService.save(task);

        return ResponseEntity.ok("Task completed!");

    }
}
