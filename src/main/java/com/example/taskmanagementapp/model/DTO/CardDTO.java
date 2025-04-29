package com.example.taskmanagementapp.model.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class CardDTO {
    private int internalKey;

    @NotBlank(message = "Name can't be empty")
    private String name;
    private String notes;
    private List<String> taskNames;
}
