package com.example.taskmanagementapp.service.mapper;

import com.example.taskmanagementapp.model.Card;
import com.example.taskmanagementapp.model.DTO.TaskDTO;
import com.example.taskmanagementapp.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskDTO taskToDto(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setInternalKey(task.getId());
        taskDTO.setName(task.getName());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setDone(task.getDone());
        taskDTO.setCardId(task.getCard().getId());

        return taskDTO;
    }

    public Task dtoToTask(TaskDTO taskDTO, Card card){
        Task task = new Task();
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setDone(taskDTO.getDone());
        task.setCard(card);

        return task;
    }
}
