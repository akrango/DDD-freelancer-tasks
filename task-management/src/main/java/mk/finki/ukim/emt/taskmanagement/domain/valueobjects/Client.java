package mk.finki.ukim.emt.taskmanagement.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.finki.ukim.emt.sharedkernel.domain.base.ValueObject;
import mk.finki.ukim.emt.sharedkernel.person.EmailAddress;
import mk.finki.ukim.emt.sharedkernel.person.PersonName;
@Getter
public class Client implements ValueObject {
    private final ClientId id;
    private final PersonName clientName;
    private final EmailAddress emailAddress;

    private Client() {
        this.id = ClientId.randomId(ClientId.class);
        this.clientName = new PersonName("","");
        this.emailAddress = new EmailAddress("");
    }

    @JsonCreator
    public Client(@JsonProperty("id") ClientId id,
                  @JsonProperty("name") PersonName freelancerName,
                  @JsonProperty("email") EmailAddress emailAddress) {
        this.id = id;
        this.clientName = freelancerName;
        this.emailAddress = emailAddress;
    }
}
