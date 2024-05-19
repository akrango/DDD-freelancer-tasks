package mk.finki.ukim.emt.taskmanagement.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import mk.finki.ukim.emt.sharedkernel.domain.base.AbstractEntity;
import mk.finki.ukim.emt.sharedkernel.domain.base.DomainObjectId;
import mk.finki.ukim.emt.sharedkernel.finance.Money;
import mk.finki.ukim.emt.taskmanagement.domain.valueobjects.FreelancerId;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "bids")
public class Bid extends AbstractEntity<BidId> {

    private String proposal;
    @Column(name = "timestamp", nullable = false)
    private LocalDate timestamp;
    private Money amount;
    @AttributeOverride(name = "id", column = @Column(name = "task_id", nullable = false))
    private TaskId taskId;
    @AttributeOverride(name = "id", column = @Column(name = "freelancer_id", nullable = false))
    private FreelancerId freelancerId;


    public Bid(@NonNull TaskId taskId,
               @NonNull FreelancerId freelancerId,
               @NonNull String proposal,
               @NonNull Money amount,
               @NonNull LocalDate timestamp) {
        super(BidId.randomId(BidId.class));
        this.freelancerId = freelancerId;
        this.taskId = taskId;
        this.proposal = proposal;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    private Bid() {
        super(BidId.randomId(BidId.class));
    }

}
