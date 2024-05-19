package mk.finki.ukim.emt.taskmanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import mk.finki.ukim.emt.sharedkernel.domain.base.AbstractEntity;
import mk.finki.ukim.emt.sharedkernel.finance.Money;
import mk.finki.ukim.emt.sharedkernel.person.EmailAddress;
import mk.finki.ukim.emt.sharedkernel.person.PersonName;
import mk.finki.ukim.emt.taskmanagement.domain.valueobjects.ClientId;
import mk.finki.ukim.emt.taskmanagement.domain.valueobjects.Freelancer;
import mk.finki.ukim.emt.taskmanagement.domain.valueobjects.FreelancerId;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Table(name = "task")
public class Task extends AbstractEntity<TaskId> {
    private Money budget;
    @Column(name = "deadline", nullable = false)
    private LocalDate deadline;
    @Column(name = "title", nullable = false)
    private String title;
    private String description;
    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TaskStatus status;
    @AttributeOverride(name = "id", column = @Column(name = "client_id", nullable = false))
    private ClientId clientId;
    @AttributeOverride(name = "id", column = @Column(name = "freelancer_id", nullable = true))
    private FreelancerId freelancerId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Bid> bids;

    private Task() {
        super(TaskId.randomId(TaskId.class));
    }

    public Task(Money budget,
                LocalDate deadline,
                String title,
                String description,
//                TaskStatus status,
                ClientId clientId,
                FreelancerId freelancerId) {
        super(TaskId.randomId(TaskId.class));

        this.budget = budget;
        this.deadline = deadline;
        this.title = title;
        this.description = description;
        this.status = TaskStatus.OPEN;
        this.clientId = clientId;
        this.freelancerId = freelancerId;
    }

    @JsonCreator
    public Task(@JsonProperty("id") TaskId id,
                @JsonProperty("name") Money budget,
                @JsonProperty("deadline") LocalDate deadline,
                @JsonProperty("title") String title,
                @JsonProperty("description") String description,
                @JsonProperty("clientId") ClientId clientId,
                @JsonProperty("freelancerId") FreelancerId freelancerId) {
        super(id);
        this.budget = budget;
        this.title = title;
        this.description = description;
        this.clientId = clientId;
        this.freelancerId = freelancerId;
    }

    public static Task build(Money budget,
                             LocalDate deadline,
                             String title,
                             String description,
                             ClientId clientId,
                             FreelancerId freelancerId) {
        Task task = new Task();
        task.budget = budget;
        task.deadline = deadline;
        task.description = description;
        task.clientId = clientId;
        task.freelancerId = freelancerId;
        return task;
    }

    public Bid addBid(@NonNull Task task,
                      @NonNull Freelancer freelancer,
                      @NonNull String proposal,
                      @NonNull Money amount,
                      @NonNull LocalDate timestamp) {
        Objects.requireNonNull(freelancer, "freelancer must not be null");
        var bid = new Bid(task.getId(), freelancer.getId(), proposal, amount, timestamp);
        bids.add(bid);
        return bid;
    }

    public void removeBid(@NonNull BidId bidId) {
        Objects.requireNonNull(bidId, "bidId must not be null");
        bids.removeIf(v -> v.getId().equals(bidId));
    }

}
