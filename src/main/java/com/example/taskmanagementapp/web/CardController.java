package com.example.taskmanagementapp.web;


import com.example.taskmanagementapp.model.Card;
import com.example.taskmanagementapp.model.DTO.CardDTO;
import com.example.taskmanagementapp.model.DTO.TaskDTO;
import com.example.taskmanagementapp.model.Task;
import com.example.taskmanagementapp.service.CardService;
import com.example.taskmanagementapp.service.TaskService;
import com.example.taskmanagementapp.service.mapper.CardMapper;
import com.example.taskmanagementapp.service.mapper.TaskMapper;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cards")
public class CardController {
    private CardService cardService;
    private CardMapper cardMapper;
    private TaskMapper taskMapper;
    private TaskService taskService;

    public CardController(CardService cardService, CardMapper cardMapper, TaskMapper taskMapper, TaskService taskService) {
        this.cardService = cardService;
        this.cardMapper = cardMapper;
        this.taskMapper = taskMapper;
        this.taskService = taskService;
    }

    @GetMapping
    public String listAllCards(Model model){
        List<Card> cards = cardService.getAllCards();

        Card backlog = cardService.findCardByName("Backlog");

        cards.remove(backlog);

        List<CardDTO> cardsDto= cards.stream().map(card -> cardMapper.toCardDTO(card)).collect(Collectors.toList());

        model.addAttribute("cardList", cardsDto);

        return "allCardsPage";
    }

    @GetMapping("/open")
    public String openCard(@RequestParam int id, Model model){

        Card card = cardService.findCardById(id);
        List<Task> tasks = card.getTasks();

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setCardId(card.getId());

        CardDTO cardDTO= cardMapper.toCardDTO(card);
        List<TaskDTO> taskDTOS = tasks.stream().map(task -> taskMapper.taskToDto(task)).collect(Collectors.toList());

        model.addAttribute("card", cardDTO);
        model.addAttribute("tasks", taskDTOS);
        model.addAttribute("task", taskDTO);

        return "cardDetails";
    }

    @PostMapping("{id}/delete")
    public String deleteCard(@PathVariable(value = "id") int id){
        Card card = cardService.findCardById(id);
        cardService.delete(card);

        return "redirect:/home";
    }

    @GetMapping("/create")
    public String showCardForm(Model model){

        model.addAttribute("card", new CardDTO());

        return "createNewCard";
    }

    @PostMapping("/process")
    public String proccessCardForm(@Valid @ModelAttribute("card") CardDTO cardDTO, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "createNewCard";
        }else {
            List<Task> tasks = new ArrayList<>();

            Card card = cardMapper.toCard(cardDTO, tasks);

            cardService.save(card);

            return "redirect:/home";
        }
    }

    @PostMapping("/newTask")
    public String addNewTaskInCard(@ModelAttribute("task") TaskDTO taskDTO){
        Card card = cardService.findCardById(taskDTO.getCardId());

        Task task = taskMapper.dtoToTask(taskDTO, card);

        task.setDone(false);

        taskService.save(task);

        return "redirect:/cards/open?id=" + card.getId();
    }


}
