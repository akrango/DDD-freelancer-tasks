package mk.finki.ukim.emt.clientmanagement.service.forms;

import lombok.Data;
import mk.finki.ukim.emt.sharedkernel.person.EmailAddress;
import mk.finki.ukim.emt.sharedkernel.person.PersonName;

@Data
public class ClientForm {
    private PersonName name;
    private EmailAddress emailAddress;
}
