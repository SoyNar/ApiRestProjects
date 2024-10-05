package com.riwi.riwiproject.Infrastructure.Adapters.Out.Persistence;

import com.riwi.riwiproject.domain.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITaskRepository extends JpaRepository<Task,Long> {
}
