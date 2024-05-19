package mk.finki.ukim.emt.sharedkernel.domain.events;

import lombok.Getter;
import mk.finki.ukim.emt.sharedkernel.domain.config.TopicHolder;
import mk.finki.ukim.emt.sharedkernel.finance.Money;

import java.time.LocalDate;

@Getter
public class BidCreated extends DomainEvent {

    private String freelancerId;
    private String taskId;
    private Money amount;
    private LocalDate timestamp;
    private String proposal;

    public BidCreated(String topic) {
        super(TopicHolder.TOPIC_BID_CREATED);
    }

    public BidCreated(String freelancerId, String taskId, String proposal, Money amount, LocalDate timestamp) {
        super(TopicHolder.TOPIC_BID_CREATED);
        this.freelancerId=freelancerId;
        this.taskId = taskId;
        this.proposal = proposal;
        this.amount = amount;
        this.timestamp = timestamp;
    }
}