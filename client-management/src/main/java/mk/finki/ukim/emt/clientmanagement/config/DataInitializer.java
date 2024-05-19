package mk.finki.ukim.emt.clientmanagement.config;


import mk.finki.ukim.emt.clientmanagement.domain.models.Client;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import mk.finki.ukim.emt.clientmanagement.domain.models.repository.ClientRepository;
import mk.finki.ukim.emt.sharedkernel.person.EmailAddress;
import mk.finki.ukim.emt.sharedkernel.person.PersonName;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInitializer {
    private final ClientRepository clientRepository;

    @PostConstruct
    public void initData(){
        if(clientRepository.findAll().isEmpty()){
            clientRepository.saveAndFlush(Client.build(new PersonName("ClientName1", "ClientSurname1"), new EmailAddress("email1@gmail.com")));
            clientRepository.saveAndFlush(Client.build(new PersonName("ClientName2", "ClientSurname2"), new EmailAddress("email2@gmail.com")));
            clientRepository.saveAndFlush(Client.build(new PersonName("ClientName3", "ClientSurname3"), new EmailAddress("email3@gmail.com")));
            clientRepository.saveAndFlush(Client.build(new PersonName("ClientName4", "ClientSurname4"), new EmailAddress("email4@gmail.com")));
            clientRepository.saveAndFlush(Client.build(new PersonName("ClientName5", "ClientSurname5"), new EmailAddress("email5@gmail.com")));
            clientRepository.saveAndFlush(Client.build(new PersonName("ClientName6", "ClientSurname6"), new EmailAddress("email6@gmail.com")));

        }

    }
}