package mk.finki.ukim.emt.taskmanagement.domain.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.NonNull;
import mk.finki.ukim.emt.sharedkernel.domain.base.DomainObjectId;
@Embeddable
public class ClientId extends DomainObjectId {

    public ClientId() {
        super(ClientId.randomId(ClientId.class).getId());
    }

    public ClientId(@NonNull String uuid) {
        super(uuid);
    }

}
