package mk.finki.ukim.emt.taskmanagement.domain.service.impl;

import lombok.AllArgsConstructor;
import mk.finki.ukim.emt.sharedkernel.domain.events.BidCreated;
import mk.finki.ukim.emt.sharedkernel.domain.events.BidRemoved;
import mk.finki.ukim.emt.sharedkernel.domain.infra.DomainEventPublisher;
import mk.finki.ukim.emt.sharedkernel.finance.Money;
import mk.finki.ukim.emt.taskmanagement.domain.exceptions.BidIdNotExistException;
import mk.finki.ukim.emt.taskmanagement.domain.exceptions.TaskIdNotExistException;
import mk.finki.ukim.emt.taskmanagement.domain.model.Bid;
import mk.finki.ukim.emt.taskmanagement.domain.model.BidId;
import mk.finki.ukim.emt.taskmanagement.domain.model.Task;
import mk.finki.ukim.emt.taskmanagement.domain.model.TaskId;
import mk.finki.ukim.emt.taskmanagement.domain.repository.BidRepository;
import mk.finki.ukim.emt.taskmanagement.domain.repository.TaskRepository;
import mk.finki.ukim.emt.taskmanagement.domain.service.TaskService;
import mk.finki.ukim.emt.taskmanagement.domain.service.forms.BidForm;
import mk.finki.ukim.emt.taskmanagement.domain.service.forms.TaskForm;
import mk.finki.ukim.emt.taskmanagement.domain.valueobjects.Freelancer;
import mk.finki.ukim.emt.taskmanagement.domain.valueobjects.FreelancerId;
import mk.finki.ukim.emt.taskmanagement.xport.rest.FreelancerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final BidRepository bidRepository;
    private final Validator validator;
    private final DomainEventPublisher domainEventPublisher;
    @Autowired
    private FreelancerClient freelancerClient;


    @Override
    public TaskId createTask(TaskForm taskForm) {
        Objects.requireNonNull(taskForm, "task must not be null");
        var constraintValidation = validator.validate(taskForm);
        if (constraintValidation.size()>0){
            throw new ConstraintViolationException("the task form is not valid", constraintValidation);
        }
        var newTask=taskRepository.saveAndFlush(toDomainObject(taskForm));
//        Freelancer freelancer=freelancerClient.findById(newTask.getFreelancerId()).orElseThrow(RuntimeException::new);
//        domainEventPublisher.publish(new TaskCreated(freelancer.getId().toString(), newTask.getBudget(), newTask.getDeadline(), newTask.getTitle(), newTask.getDescription()));
        return newTask.getId();
    }

    private Task toDomainObject(TaskForm taskForm){
        var task = new Task(taskForm.getBudget(), taskForm.getDeadline(), taskForm.getTitle(), taskForm.getDescription(), taskForm.getClient().getId(), taskForm.getFreelancer().getId());
        return task;
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Optional<Task> findById(TaskId taskId) {
        return taskRepository.findById(taskId);
    }

    @Override
    public void addBid(TaskId taskId, BidForm bidForm) throws TaskIdNotExistException {
        Task task=taskRepository.findById(taskId).orElseThrow(TaskIdNotExistException::new);
        task.addBid(bidForm.getTask(), bidForm.getFreelancer(), bidForm.getProposal(), bidForm.getAmount(), bidForm.getTimestamp());
        taskRepository.saveAndFlush(task);

        //domainEventPublisher.publish(new BidCreated(bidForm.getFreelancer().getId().toString(), bidForm.getTask().getId().toString(), bidForm.getProposal(), bidForm.getAmount(), bidForm.getTimestamp()));

    }

    @Override
    public void deleteBid(TaskId taskId, BidId bidId) throws TaskIdNotExistException, BidIdNotExistException {
        Task task=taskRepository.findById(taskId).orElseThrow(TaskIdNotExistException::new);
        task.removeBid(bidId);
        taskRepository.saveAndFlush(task);
//        domainEventPublisher.publish(new BidRemoved(bidForm.getFreelancer().getId().toString(), bidForm.getTask().getId().toString(), bidForm.getProposal(), bidForm.getAmount(), bidForm.getTimestamp()));
    }

    @Override
    public Task bidCreated(FreelancerId freelancerId, TaskId taskId, String proposal, Money amount, LocalDate timestamp) {
        Task t = taskRepository.findById(taskId).orElseThrow(TaskIdNotExistException::new);
        Freelancer f= freelancerClient.findById(freelancerId).orElseThrow(RuntimeException::new);
        t.addBid(t, f, proposal, amount, timestamp);
        taskRepository.saveAndFlush(t);
        return t;
    }

    @Override
    public Task bidRemoved(BidId bidId, FreelancerId freelancerId, TaskId taskId, String proposal, Money amount, LocalDate timestamp) {
        Task t = taskRepository.findById(taskId).orElseThrow(TaskIdNotExistException::new);
        Freelancer f= freelancerClient.findById(freelancerId).orElseThrow(RuntimeException::new);
        t.removeBid(bidId);
        taskRepository.saveAndFlush(t);
        return t;
    }
}
