package com.assignment.service.impl;

import com.assignment.constants.Status;
import com.assignment.constants.TaskPriority;
import com.assignment.dto.TaskDto;
import com.assignment.dto.request.TaskRequest;
import com.assignment.dto.response.TaskResponse;
import com.assignment.entity.TaskEntity;
import com.assignment.exceptionHandler.TaskResponseException;
import com.assignment.repository.TaskRepository;
import com.assignment.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskDto taskDto;

    @Override
    public TaskResponse saveTask(TaskRequest taskRequest) {
        TaskResponse taskResponse = null;
        if (Objects.nonNull(taskRequest)) {
            TaskEntity taskEntity = taskDto.requestToEntity(taskRequest);
            TaskEntity savedTask = taskRepository.save(taskEntity);
            taskResponse = taskDto.entityToResponse(savedTask);
        }
        return taskResponse;
    }

    @Override
    @Cacheable(value="taskCache", key="#id")
    public TaskResponse getTaskById(Long id) throws TaskResponseException {
        Optional<TaskEntity> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            TaskEntity taskEntity = optionalTask.get();
            return taskDto.entityToResponse(taskEntity);
        } else {
            throw new TaskResponseException("Task Not Found for ID: " + id);
        }
    }


    @Override
    public List<TaskResponse> getTasksByPriority(TaskPriority priority) throws TaskResponseException {
        List<TaskEntity> taskEntities = taskRepository.findByPriority(priority.name());
        if (taskEntities.isEmpty()) {
            throw new TaskResponseException("No Task Found");
        }
        return taskEntities.stream()
                .map(task -> taskDto.entityToResponse(task))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskResponse> getAllTask() throws TaskResponseException {
        List<TaskEntity> taskEntityList = taskRepository.findAll();
        if (taskEntityList.isEmpty()) {
            throw new TaskResponseException("No Task Found");
        }
        return taskEntityList.stream()
                .map(task -> taskDto.entityToResponse(task))
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(value="taskCache", key="#id")
    public TaskResponse updateTask(Long id, TaskRequest taskRequest) throws TaskResponseException {
        Optional<TaskEntity> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            TaskEntity taskEntity = optionalTask.get();
            taskEntity = taskDto.updateEntity(taskRequest, taskEntity);
            taskEntity = taskRepository.save(taskEntity);
            return taskDto.entityToResponse(taskEntity);
        } else {
            throw new TaskResponseException("Program Not Found");
        }
    }

    @Override
    public Status deleteTask(Long id) {
        Optional<TaskEntity> task = taskRepository.findById(id);
        if (task.isPresent()) {
            taskRepository.deleteById(id);
            return Status.SUCCESS;
        }
        return Status.FAILED;
    }
}
