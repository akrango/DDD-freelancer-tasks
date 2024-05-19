package mk.finki.ukim.emt.sharedkernel.person;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import mk.finki.ukim.emt.sharedkernel.domain.base.ValueObject;

@Embeddable
@Getter
public class EmailAddress implements ValueObject {
    private String emailAddress;

    public EmailAddress(String email) {
        emailAddress=email;
    }

    public EmailAddress() {
        emailAddress="";
    }

    @Override
    public String toString() {
        return "EmailAddress{" +
                "emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
