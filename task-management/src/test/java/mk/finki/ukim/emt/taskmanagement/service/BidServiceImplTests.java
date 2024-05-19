package mk.finki.ukim.emt.taskmanagement.service;

import mk.finki.ukim.emt.sharedkernel.finance.Currency;
import mk.finki.ukim.emt.sharedkernel.finance.Money;
import mk.finki.ukim.emt.sharedkernel.person.EmailAddress;
import mk.finki.ukim.emt.sharedkernel.person.PersonName;
import mk.finki.ukim.emt.taskmanagement.domain.exceptions.BidIdNotExistException;
import mk.finki.ukim.emt.taskmanagement.domain.exceptions.TaskIdNotExistException;
import mk.finki.ukim.emt.taskmanagement.domain.model.Bid;
import mk.finki.ukim.emt.taskmanagement.domain.model.BidId;
import mk.finki.ukim.emt.taskmanagement.domain.model.Task;
import mk.finki.ukim.emt.taskmanagement.domain.model.TaskId;
import mk.finki.ukim.emt.taskmanagement.domain.service.BidService;
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
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@SpringBootTest
public class BidServiceImplTests {

    @Autowired
    private TaskService taskService;

    @Autowired
    private BidService bidService;
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

    private static Task newTask(Money budget, LocalDate deadline, String title, String description, ClientId clientId, FreelancerId freelancerId) {
        Task t = new Task(TaskId.randomId(TaskId.class), budget, deadline, title, description, clientId, freelancerId);
        return t;
    }


    @Test
    public void testCreateBid() {

        BidForm bidForm = new BidForm();
        bidForm.setAmount(Money.valueOf(Currency.MKD, 2500));
        bidForm.setProposal("Proposal 1");
        bidForm.setTimestamp(LocalDate.now());
        bidForm.setFreelancer(newFreelancer("Name1", "Surname1", "email@gmail.com", "Skills1"));
        Client c1 = newClient("Client1", "Sur1", "email1@gmail");
        Freelancer f1 = newFreelancer("Free1", "Sur1", "email1@gmail", "skill1");
        bidForm.setTask(newTask(Money.valueOf(Currency.MKD, 5500), LocalDate.now(), "Title", "Desc1", c1.getId(), f1.getId()));

        try {
            // Attempt to create the task
            BidId newBidId = bidService.createBid(bidForm);
            Bid newBid = bidService.findById(newBidId).orElseThrow(BidIdNotExistException::new);

            Assertions.assertEquals(newBid, bidService.findById(newBidId).get());
        } catch (ConstraintViolationException e) {
            // Print out the validation errors
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            for (ConstraintViolation<?> violation : violations) {
                System.out.println("Validation error: " + violation.getPropertyPath() + " " + violation.getMessage());
            }
        }
    }

    @Test
    public void testCreateBidWithRealData() {
        List<Task> taskList = taskService.findAll();
        Task t1 = taskList.get(0);
        Task t2 = taskList.get(1);

        List<Freelancer> freelancerList = freelancerClient.findAll();
        Freelancer f1 = freelancerList.get(0);
        Freelancer f2 = freelancerList.get(1);

        BidForm bidForm = new BidForm();
        bidForm.setAmount(Money.valueOf(Currency.MKD, 2500));
        bidForm.setProposal("Proposal 1");
        bidForm.setTimestamp(LocalDate.now());
        bidForm.setFreelancer(f1);
        bidForm.setTask(t1);


        try {
            // Attempt to create the task
            BidId newBidId = bidService.createBid(bidForm);
            Bid newBid = bidService.findById(newBidId).orElseThrow(BidIdNotExistException::new);

            Assertions.assertEquals(newBid, bidService.findById(newBidId).get());
        } catch (ConstraintViolationException e) {
            // Print out the validation errors
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            for (ConstraintViolation<?> violation : violations) {
                System.out.println("Validation error: " + violation.getPropertyPath() + " " + violation.getMessage());
            }
        }

    }

}
