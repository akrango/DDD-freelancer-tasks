package mk.finki.ukim.emt.taskmanagement.domain.service.forms;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import mk.finki.ukim.emt.sharedkernel.finance.Money;
import mk.finki.ukim.emt.taskmanagement.domain.model.Task;
import mk.finki.ukim.emt.taskmanagement.domain.valueobjects.Freelancer;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Getter
public class BidForm {
    @NotNull
    private String proposal;
    @NotNull
    private LocalDate timestamp;
    @NotNull
    private Money amount;
    @NotNull
    private Task task;
    @NotNull
    private Freelancer freelancer;
}
