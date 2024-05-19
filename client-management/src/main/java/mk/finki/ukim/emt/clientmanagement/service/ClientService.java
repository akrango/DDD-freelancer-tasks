package mk.finki.ukim.emt.clientmanagement.service;

import mk.finki.ukim.emt.clientmanagement.domain.models.Client;
import mk.finki.ukim.emt.clientmanagement.domain.models.ClientId;
import mk.finki.ukim.emt.clientmanagement.service.forms.ClientForm;

import java.util.List;

public interface ClientService {
    Client findById(ClientId clientId);
    Client createClient(ClientForm clientForm);
//    Client taskCreated(ClientId, )
    List<Client> findAll();
}
