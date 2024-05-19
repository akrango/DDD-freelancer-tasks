package mk.finki.ukim.emt.taskmanagement.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.finki.ukim.emt.sharedkernel.domain.base.ValueObject;
import mk.finki.ukim.emt.sharedkernel.person.EmailAddress;
import mk.finki.ukim.emt.sharedkernel.person.PersonName;

@Getter
public class Freelancer implements ValueObject {
    private final FreelancerId id;
    private final PersonName freelancerName;
    private final EmailAddress emailAddress;
    private final String skills;

    private Freelancer() {
        this.id = FreelancerId.randomId(FreelancerId.class);
        this.skills = "";
        this.freelancerName = new PersonName("", "");
        this.emailAddress = new EmailAddress("");
    }

    @JsonCreator
    public Freelancer(@JsonProperty("id") FreelancerId id,
                      @JsonProperty("name") PersonName freelancerName,
                      @JsonProperty("email") EmailAddress emailAddress,
                      @JsonProperty("skills") String skills) {
        this.id = id;
        this.freelancerName = freelancerName;
        this.emailAddress = emailAddress;
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Freelancer{" +
                "id=" + id +
                ", freelancerName=" + freelancerName +
                ", emailAddress=" + emailAddress +
                ", skills='" + skills + '\'' +
                '}';
    }
}
