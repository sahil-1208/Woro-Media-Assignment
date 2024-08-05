package com.assignment.dto;

import com.assignment.dto.request.TaskRequest;
import com.assignment.dto.response.TaskResponse;
import com.assignment.entity.TaskEntity;
import com.assignment.entity.UserEntity;
import com.assignment.exceptionHandler.UserResponseException;
import com.assignment.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskDto {

    @Autowired
    private UserRepository userRepository;

    public TaskEntity requestToEntity(TaskRequest taskRequest) {
        TaskEntity taskEntity = new TaskEntity();
        UserEntity userEntity = findUserById(taskRequest.getUserId());
        taskEntity.setUser(userEntity);
        BeanUtils.copyProperties(taskRequest, taskEntity);
        return taskEntity;
    }

    public TaskResponse entityToResponse(TaskEntity taskEntity) {
        TaskResponse taskResponse = new TaskResponse();
        BeanUtils.copyProperties(taskEntity, taskResponse);
        taskResponse.setUserId(taskEntity.getUser().getId());
        return taskResponse;
    }

    public TaskEntity updateEntity(TaskRequest taskRequest, TaskEntity taskEntity) {
        UserEntity userEntity = findUserById(taskRequest.getUserId());
        taskEntity.setUser(userEntity);
        BeanUtils.copyProperties(taskRequest, taskEntity);
        return taskEntity;
    }

    private UserEntity findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserResponseException("User not found"));
    }

}
