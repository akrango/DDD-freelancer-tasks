package mk.finki.ukim.emt.taskmanagement.domain.service;

import mk.finki.ukim.emt.taskmanagement.domain.exceptions.BidIdNotExistException;
import mk.finki.ukim.emt.taskmanagement.domain.exceptions.TaskIdNotExistException;
import mk.finki.ukim.emt.taskmanagement.domain.model.BidId;
import mk.finki.ukim.emt.taskmanagement.domain.model.Task;
import mk.finki.ukim.emt.taskmanagement.domain.model.TaskId;
import mk.finki.ukim.emt.taskmanagement.domain.service.forms.BidForm;
import mk.finki.ukim.emt.taskmanagement.domain.service.forms.TaskForm;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    TaskId createTask(TaskForm taskForm);
    List<Task> findAll();
    Optional<Task> findById(TaskId taskId);
    void addBid(TaskId taskId, BidForm bidForm) throws TaskIdNotExistException;
    void deleteBid(TaskId taskId, BidId bidId) throws TaskIdNotExistException, BidIdNotExistException;
}
