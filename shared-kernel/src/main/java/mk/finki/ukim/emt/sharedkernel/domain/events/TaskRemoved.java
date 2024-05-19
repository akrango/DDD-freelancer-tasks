package mk.finki.ukim.emt.sharedkernel.domain.events;

import lombok.Getter;
import mk.finki.ukim.emt.sharedkernel.domain.config.TopicHolder;
import mk.finki.ukim.emt.sharedkernel.finance.Money;

import java.time.LocalDate;

//@Getter
//public class TaskRemoved extends DomainEvent{
//
//    private String freelancerId;
//    private Money budget;
//    private LocalDate deadline;
//    private String title;
//    private String description;
//    public TaskRemoved(String topic) {
//        super(TopicHolder.T);
//    }
//    public TaskRemoved(String freelancerId, Money budget, LocalDate deadline, String title, String description) {
//        super(TopicHolder.TOPIC_ORDER_ITEM_REMOVED);
//        this.freelancerId=freelancerId;
//        this.budget = budget;
//        this.deadline = deadline;
//        this.title = title;
//        this.description = description;
//    }
//}
