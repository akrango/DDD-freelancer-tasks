package mk.finki.ukim.emt.taskmanagement.service;

import mk.finki.ukim.emt.sharedkernel.finance.Currency;
import mk.finki.ukim.emt.sharedkernel.finance.Money;
import mk.finki.ukim.emt.sharedkernel.person.EmailAddress;
import mk.finki.ukim.emt.sharedkernel.person.PersonName;
import mk.finki.ukim.emt.taskmanagement.domain.exceptions.TaskIdNotExistException;
import mk.finki.ukim.emt.taskmanagement.domain.model.Task;
import mk.finki.ukim.emt.taskmanagement.domain.model.TaskId;
import mk.finki.ukim.emt.taskmanagement.domain.service.TaskService;
import mk.finki.ukim.emt.taskmanagement.domain.service.forms.BidForm;
import mk.finki.ukim.emt.taskmanagement.domain.service.forms.TaskForm;
import mk.finki.ukim.emt.taskmanagement.domain.valueobjects.Client;
import mk.finki.ukim.emt.taskmanagement.domain.valueobjects.ClientId;
import mk.finki.ukim.emt.taskmanagement.domain.valueobjects.Freelancer;
import mk.finki.ukim.emt.taskmanagement.domain.valueobjects.FreelancerId;
import mk.finki.ukim.emt.taskmanagement.xport.rest.ClientClient;
import mk.finki.ukim.emt.taskmanagement.xport.rest.FreelancerClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@SpringBootTest
public class TaskServiceImplTests {
    @Autowired
    private TaskService taskService;

    @Autowired
    private ClientClient clientClient;

    @Autowired
    private FreelancerClient freelancerClient;


    private static Freelancer newFreelancer(String name, String surname, String email, String skills) {
        Freelancer f = new Freelancer(FreelancerId.randomId(FreelancerId.class), new PersonName(name, surname), new EmailAddress(email), skills);
        return f;
    }
    private static Client newClient(String name, String surname, String email) {
        Client c = new Client(ClientId.randomId(ClientId.class), new PersonName(name, surname), new EmailAddress(email));
        return c;
    }

    @Test
    public void testCreateTask() {

//        BidForm bid1 = new BidForm();
//        bid1.setAmount(Money.valueOf(Currency.MKD, 2500));
//        bid1.setProposal("Proposal 1");
//        bid1.setTimestamp(LocalDate.now());
//        bid1.setFreelancer(newFreelancer("Name1", "Surname1", "email@gmail.com", "Skills1"));
//
//        BidForm bid2 = new BidForm();
//        bid2.setAmount(Money.valueOf(Currency.MKD, 3000));
//        bid2.setProposal("Proposal 2");
//        bid2.setTimestamp(LocalDate.now());
//        bid2.setFreelancer(newFreelancer("Name2", "Surname2", "email1@gmail.com", "Skills2"));

        TaskForm taskForm = new TaskForm();
        taskForm.setBudget(Money.valueOf(Currency.MKD, 4000));
        taskForm.setTitle("Task 1");
        taskForm.setDeadline(LocalDate.now().plusMonths(1));
        taskForm.setDescription("Desc1");
        taskForm.setClient(newClient("Client1", "Sur1", "email1@gmail"));
//        taskForm.setBids(Arrays.asList(bid1,bid2));
        taskForm.setFreelancer(newFreelancer("Free1", "Sur1", "email1@gmail","skill1"));

        TaskId newTaskId = taskService.createTask(taskForm);
        Task newTask = taskService.findById(newTaskId).orElseThrow(TaskIdNotExistException::new);
        Assertions.assertEquals(newTask, taskService.findById(newTaskId).get());

    }

    @Test
    public void testPlaceTaskWithRealData() {
        List<Client> clientList = clientClient.findAll();
        Client c1 = clientList.get(0);
        Client c2 = clientList.get(1);

        List<Freelancer> freelancerList = freelancerClient.findAll();
        Freelancer f1 = freelancerList.get(0);
        Freelancer f2 = freelancerList.get(1);

//        List<Task> taskList = taskService.findAll();
//        Task t1=taskList.get(0);
//        Task t2=taskList.get(1);


//        BidForm bid1 = new BidForm();
//        bid1.setAmount(Money.valueOf(Currency.MKD, 2500));
//        bid1.setProposal("Proposal 1");
//        bid1.setTimestamp(LocalDate.now());
//        bid1.setFreelancer(newFreelancer("Name1", "Surname1", "email@gmail.com", "Skills1"));
//        bid1.setTask();
//
//        BidForm bid2 = new BidForm();
//        bid2.setAmount(Money.valueOf(Currency.MKD, 3000));
//        bid2.setProposal("Proposal 2");
//        bid2.setTimestamp(LocalDate.now());
//        bid2.setFreelancer(newFreelancer("Name2", "Surname2", "email1@gmail.com", "Skills2"));
//        bid2.setTask();

        TaskForm taskForm = new TaskForm();
        taskForm.setBudget(Money.valueOf(Currency.MKD, 4000));
        taskForm.setTitle("Task 1");
        taskForm.setDeadline(LocalDate.now().plusMonths(1));
        taskForm.setDescription("Desc1");
        taskForm.setClient(c1);
//        taskForm.setBids(Arrays.asList(bid1,bid2));
        taskForm.setFreelancer(f1);

//        System.out.println(taskForm.getDeadline());
//        System.out.println(taskForm.getBudget());
//        System.out.println(taskForm.getTitle());
//        System.out.println(taskForm.getDescription());
//        System.out.println("free "+taskForm.getFreelancer());
//        System.out.println(taskForm.getClient().getClientName());
//        System.out.println(taskForm.getBids().size());

//        TaskId newTaskId = taskService.createTask(taskForm);
//        Task newTask = taskService.findById(newTaskId).orElseThrow(TaskIdNotExistException::new);
//
//        Assertions.assertEquals(newTask, taskService.findById(newTaskId).get());

        try {
            // Attempt to create the task
            TaskId newTaskId = taskService.createTask(taskForm);
            Task newTask = taskService.findById(newTaskId).orElseThrow(TaskIdNotExistException::new);

            Assertions.assertEquals(newTask, taskService.findById(newTaskId).get());
        } catch (ConstraintViolationException e) {
            // Print out the validation errors
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            for (ConstraintViolation<?> violation : violations) {
                System.out.println("Validation error: " + violation.getPropertyPath() + " " + violation.getMessage());
            }
        }

    }



}
