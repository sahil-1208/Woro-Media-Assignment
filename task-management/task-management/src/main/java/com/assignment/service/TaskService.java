package com.assignment.service;


import com.assignment.constants.Status;
import com.assignment.constants.TaskPriority;
import com.assignment.dto.request.TaskRequest;
import com.assignment.dto.response.TaskResponse;
import com.assignment.exceptionHandler.TaskResponseException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {

    TaskResponse saveTask(TaskRequest taskRequest);

    TaskResponse getTaskById(Long id) throws TaskResponseException;

    List<TaskResponse> getTasksByPriority(TaskPriority priority) throws TaskResponseException;

    List<TaskResponse> getAllTask() throws TaskResponseException;

    TaskResponse updateTask(Long id,TaskRequest taskRequest) throws TaskResponseException;

    Status deleteTask(Long id);

}
