package com.example.taskmanagementapp.service;

import com.example.taskmanagementapp.model.Task;


import java.util.List;

public interface TaskService {

    List<Task> getAllTasks();

    Task findTaskById(int id);

    void save(Task task);

    void delete(Task task);
}
