package mk.finki.ukim.emt.clientmanagement.domain.models.repository;

import mk.finki.ukim.emt.clientmanagement.domain.models.Client;
import mk.finki.ukim.emt.clientmanagement.domain.models.ClientId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, ClientId> {
}
