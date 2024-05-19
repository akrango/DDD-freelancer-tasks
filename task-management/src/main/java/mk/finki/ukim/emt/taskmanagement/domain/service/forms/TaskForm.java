package mk.finki.ukim.emt.taskmanagement.domain.service.forms;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import mk.finki.ukim.emt.sharedkernel.finance.Money;
import mk.finki.ukim.emt.taskmanagement.domain.model.Bid;
import mk.finki.ukim.emt.taskmanagement.domain.model.TaskStatus;
import mk.finki.ukim.emt.taskmanagement.domain.valueobjects.Client;
import mk.finki.ukim.emt.taskmanagement.domain.valueobjects.ClientId;
import mk.finki.ukim.emt.taskmanagement.domain.valueobjects.Freelancer;
import mk.finki.ukim.emt.taskmanagement.domain.valueobjects.FreelancerId;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@Getter
public class TaskForm {
    @NotNull
    private Money budget;
    @NotNull
    private LocalDate deadline;
    @NotNull
    private String title;
    @NotNull
    private String description;
//    @Enumerated(value = EnumType.STRING)
//    private TaskStatus status;
    @NotNull
    private Client client;
    private Freelancer freelancer;
    @Valid
//    @NotEmpty
    private List<BidForm> bids=new ArrayList<>();

}
