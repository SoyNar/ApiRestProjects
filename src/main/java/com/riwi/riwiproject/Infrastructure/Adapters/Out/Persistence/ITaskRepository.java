package com.riwi.riwiproject.Infrastructure.Adapters.Out.Persistence;

import com.riwi.riwiproject.domain.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ITaskRepository extends JpaRepository<Task,Long> {

    @Query("SELECT t FROM Task t WHERE t.userAsigned.username = ?1")
    List<Task> findTaskByUserAsigned(String username);
    @Query("SELECT t FROM Task t JOIN t.userAsigned u ORDER BY u.username ASC")
    List<Task> findAllTasksOrderedByUserAsigned();
}
