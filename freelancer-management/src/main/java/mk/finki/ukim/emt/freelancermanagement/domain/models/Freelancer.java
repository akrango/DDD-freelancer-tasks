package mk.finki.ukim.emt.freelancermanagement.domain.models;

import jakarta.persistence.*;
import lombok.Getter;
import mk.finki.ukim.emt.sharedkernel.domain.base.AbstractEntity;
import mk.finki.ukim.emt.sharedkernel.person.EmailAddress;
import mk.finki.ukim.emt.sharedkernel.person.PersonName;

@Entity
@Table(name = "freelancer")
@Getter
public class Freelancer extends AbstractEntity<FreelancerId> {

    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "freelancerName")),
            @AttributeOverride(name = "surname", column = @Column(name = "freelancerSurname")),

    })
    private PersonName freelancerName;
    private EmailAddress emailAddress;
    private String skills;

    private Freelancer() {
        super(FreelancerId.randomId(FreelancerId.class));
    }

    public static Freelancer build(PersonName personName, EmailAddress emailAddress, String skills) {
        Freelancer c = new Freelancer();
        c.freelancerName = personName;
        c.emailAddress = emailAddress;
        c.skills = skills;
        return c;
    }

    @Override
    public String toString() {
        return "Freelancer{" +
                "freelancerName=" + freelancerName +
                ", emailAddress=" + emailAddress +
                ", skills='" + skills + '\'' +
                '}';
    }
}
