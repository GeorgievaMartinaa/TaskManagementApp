package com.example.taskmanagementapp.service;

import com.example.taskmanagementapp.model.Card;


import java.util.List;

public interface CardService {
    List<Card> getAllCards();

    Card findCardById(int id);

    Card findCardByName(String name);

    void save(Card card);

    void delete (Card card);
}
