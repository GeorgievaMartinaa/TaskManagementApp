package com.example.taskmanagementapp.model.DTO;

import lombok.Data;

@Data
public class TaskDTO {
    private int internalKey;
    private String name;
    private String description;
    private Boolean done;
    private int cardId;

}
