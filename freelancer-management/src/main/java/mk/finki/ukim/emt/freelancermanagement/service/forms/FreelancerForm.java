package mk.finki.ukim.emt.freelancermanagement.service.forms;

import lombok.Data;
import lombok.Getter;
import mk.finki.ukim.emt.sharedkernel.person.EmailAddress;
import mk.finki.ukim.emt.sharedkernel.person.PersonName;

@Data
public class FreelancerForm {
    private PersonName freelancerName;
    private EmailAddress emailAddress;
    private String skills;
}
