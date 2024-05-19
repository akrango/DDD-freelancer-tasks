package mk.finki.ukim.emt.taskmanagement.domain.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.NonNull;
import mk.finki.ukim.emt.sharedkernel.domain.base.DomainObjectId;

@Embeddable
public class FreelancerId extends DomainObjectId {
    public FreelancerId() {
        super(FreelancerId.randomId(FreelancerId.class).getId());
    }

    public FreelancerId(@NonNull String uuid) {
        super(uuid);
    }

    public static FreelancerId of(String uuid) {
        FreelancerId p = new FreelancerId(uuid);
        return p;
    }
}
