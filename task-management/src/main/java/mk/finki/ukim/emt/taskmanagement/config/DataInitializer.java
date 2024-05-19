package mk.finki.ukim.emt.taskmanagement.config;


import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import mk.finki.ukim.emt.sharedkernel.finance.Currency;
import mk.finki.ukim.emt.sharedkernel.finance.Money;
import mk.finki.ukim.emt.taskmanagement.domain.model.Task;
import mk.finki.ukim.emt.taskmanagement.domain.repository.TaskRepository;
import mk.finki.ukim.emt.sharedkernel.person.EmailAddress;
import mk.finki.ukim.emt.sharedkernel.person.PersonName;
import mk.finki.ukim.emt.taskmanagement.domain.valueobjects.ClientId;
import mk.finki.ukim.emt.taskmanagement.domain.valueobjects.FreelancerId;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class DataInitializer {
    private final TaskRepository taskRepository;

    @PostConstruct
    public void initData(){
//        if(taskRepository.findAll().isEmpty()){
//            taskRepository.saveAndFlush(Task.build(Money.valueOf(Currency.MKD, 2000), LocalDate.now(), "Title1", "Desc1", null, null));
//            taskRepository.saveAndFlush(Task.build(Money.valueOf(Currency.MKD, 3000), LocalDate.now(), "Title2", "Desc2", null, null));
//            taskRepository.saveAndFlush(Task.build(Money.valueOf(Currency.MKD, 4000), LocalDate.now(), "Title3", "Desc3", null, null));
//            taskRepository.saveAndFlush(Task.build(Money.valueOf(Currency.MKD, 5000), LocalDate.now(), "Title4", "Desc4", null, null));
//            taskRepository.saveAndFlush(Task.build(Money.valueOf(Currency.MKD, 6000), LocalDate.now(), "Title5", "Desc5", null, null));
//        }

    }
}