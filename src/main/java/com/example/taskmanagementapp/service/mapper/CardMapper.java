package com.example.taskmanagementapp.service.mapper;

import com.example.taskmanagementapp.model.Card;
import com.example.taskmanagementapp.model.DTO.CardDTO;
import com.example.taskmanagementapp.model.Task;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CardMapper {

    public CardDTO toCardDTO(Card card){
        CardDTO cardDTO = new CardDTO();

        cardDTO.setInternalKey(card.getId());
        cardDTO.setName(card.getName());
        cardDTO.setNotes(cardDTO.getNotes());


        List<String>taskNames = card.getTasks().stream().map(Task::getName).collect(Collectors.toList());
        Collections.reverse(taskNames);

        cardDTO.setTaskNames(taskNames);

        return cardDTO;
    }

    public Card toCard(CardDTO cardDTO, List<Task> taskList){
        return new Card(cardDTO.getName(),taskList, cardDTO.getNotes());
    }
}
