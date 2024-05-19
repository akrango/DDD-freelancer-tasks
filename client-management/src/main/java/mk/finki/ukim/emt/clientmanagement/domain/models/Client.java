package mk.finki.ukim.emt.clientmanagement.domain.models;

import jakarta.persistence.*;
import lombok.Getter;
import mk.finki.ukim.emt.sharedkernel.person.EmailAddress;
import mk.finki.ukim.emt.sharedkernel.person.PersonName;
import mk.finki.ukim.emt.sharedkernel.domain.base.AbstractEntity;

@Entity
@Table(name = "client")
@Getter
public class Client extends AbstractEntity<ClientId> {

    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "clientName")),
            @AttributeOverride(name = "surname", column = @Column(name = "clientSurname")),

    })
    private PersonName clientName;
    private EmailAddress emailAddress;

    private Client(){
        super(ClientId.randomId(ClientId.class));
    }

    public static Client build(PersonName personName, EmailAddress emailAddress){
        Client c=new Client();
        c.clientName=personName;
        c.emailAddress=emailAddress;
        return c;
    }

}
