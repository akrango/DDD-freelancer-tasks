package mk.finki.ukim.emt.clientmanagement.service.impl;

import lombok.AllArgsConstructor;
import mk.finki.ukim.emt.clientmanagement.domain.models.Client;
import mk.finki.ukim.emt.clientmanagement.domain.models.ClientId;
import mk.finki.ukim.emt.clientmanagement.domain.models.exceptions.ClientNotFoundException;
import mk.finki.ukim.emt.clientmanagement.domain.models.repository.ClientRepository;
import mk.finki.ukim.emt.clientmanagement.service.ClientService;
import mk.finki.ukim.emt.clientmanagement.service.forms.ClientForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Override
    public Client findById(ClientId clientId) {
        return clientRepository.findById(clientId).orElseThrow(ClientNotFoundException::new);
    }

    @Override
    public Client createClient(ClientForm clientForm) {
        Client client=Client.build(clientForm.getName(), clientForm.getEmailAddress());
        this.clientRepository.save(client);
        return client;
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }
}
