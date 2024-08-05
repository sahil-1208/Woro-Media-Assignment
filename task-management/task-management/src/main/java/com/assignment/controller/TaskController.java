package com.assignment.controller;

import com.assignment.constants.Status;
import com.assignment.constants.TaskPriority;
import com.assignment.dto.request.TaskRequest;
import com.assignment.dto.response.TaskResponse;
import com.assignment.exceptionHandler.TaskResponseException;
import com.assignment.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/createTask")
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest taskRequest) {
        return ResponseEntity.ok().body(taskService.saveTask(taskRequest));
    }

    @Secured({"ADMIN", "USER"})
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id) throws TaskResponseException {
        return ResponseEntity.ok().body(taskService.getTaskById(id));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<TaskResponse>> getAllTasks() throws TaskResponseException {
        return ResponseEntity.ok().body(taskService.getAllTask());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/priority/{taskPriority}")
    public ResponseEntity<List<TaskResponse>> getTasksByPriority(@PathVariable TaskPriority taskPriority) throws TaskResponseException {
        return ResponseEntity.ok().body(taskService.getTasksByPriority(taskPriority));
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable Long id, @RequestBody TaskRequest programRequest)
            throws TaskResponseException {
        return ResponseEntity.ok().body(taskService.updateTask(id, programRequest));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Status> deleteTask(@PathVariable Long id) {
        return ResponseEntity.ok().body(taskService.deleteTask(id));
    }
}
