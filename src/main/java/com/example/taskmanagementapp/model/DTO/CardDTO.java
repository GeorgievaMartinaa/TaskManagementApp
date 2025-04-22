package com.example.taskmanagementapp.model.DTO;

import lombok.Data;

import java.util.List;

@Data
public class CardDTO {
    private int internalKey;
    private String name;
    private String notes;
    private List<String> taskNames;
}
