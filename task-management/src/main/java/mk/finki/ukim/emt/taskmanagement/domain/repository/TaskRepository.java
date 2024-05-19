package mk.finki.ukim.emt.taskmanagement.domain.repository;

import mk.finki.ukim.emt.taskmanagement.domain.model.Task;
import mk.finki.ukim.emt.taskmanagement.domain.model.TaskId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, TaskId> {
}
