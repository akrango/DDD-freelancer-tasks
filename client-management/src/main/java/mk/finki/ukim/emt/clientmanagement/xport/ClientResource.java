package mk.finki.ukim.emt.clientmanagement.xport;

import lombok.AllArgsConstructor;
import mk.finki.ukim.emt.clientmanagement.domain.models.Client;
import mk.finki.ukim.emt.clientmanagement.service.ClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/client")
@AllArgsConstructor
public class ClientResource {
    private final ClientService clientService;

    @GetMapping
    public List<Client> getAll() {
        return clientService.findAll();
    }

}
