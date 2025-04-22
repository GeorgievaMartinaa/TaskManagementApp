package com.example.taskmanagementapp.web;

import com.example.taskmanagementapp.model.Card;
import com.example.taskmanagementapp.model.DTO.CardDTO;
import com.example.taskmanagementapp.service.CardService;
import com.example.taskmanagementapp.service.TaskService;
import com.example.taskmanagementapp.service.mapper.CardMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = {"/", "/home"})
public class HomeController {

    private final TaskService taskService;
    private final CardService cardService;
    private final CardMapper cardMapper;

    public HomeController(TaskService taskService, CardService cardService, CardMapper cardMapper) {
        this.taskService = taskService;
        this.cardService = cardService;
        this.cardMapper = cardMapper;
    }

    @GetMapping
    public String homePage(Model model){

        List<Card> cards = cardService.getAllCards();

        Card backlog = cardService.findCardByName("Backlog");

        cards.remove(backlog);

        List<CardDTO> cardsDto= cards.stream().map(card -> cardMapper.toCardDTO(card)).collect(Collectors.toList());

        CardDTO backlogCardDto = cardMapper.toCardDTO(backlog);

        model.addAttribute("cardList", cardsDto);
        model.addAttribute("backlogCard", backlogCardDto);

        return "homePage";
    }
}
