package mk.finki.ukim.emt.clientmanagement.domain.models;

import org.springframework.lang.NonNull;

import mk.finki.ukim.emt.sharedkernel.domain.base.DomainObjectId;

public class ClientId extends DomainObjectId {

    private ClientId() {
        super(ClientId.randomId(ClientId.class).getId());
    }

    public ClientId(@NonNull String uuid) {
        super(uuid);
    }

    public static ClientId of(String uuid) {
        ClientId p = new ClientId(uuid);
        return p;
    }
}
