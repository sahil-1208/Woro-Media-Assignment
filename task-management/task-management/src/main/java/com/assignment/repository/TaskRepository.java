package com.assignment.repository;

import com.assignment.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    @Query(value = "SELECT * FROM task_entity WHERE task_priority = :taskPriority", nativeQuery = true)
    List<TaskEntity> findByPriority(@Param("taskPriority") String taskPriority);

}
