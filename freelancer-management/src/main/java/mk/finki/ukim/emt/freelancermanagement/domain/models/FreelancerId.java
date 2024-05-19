package mk.finki.ukim.emt.freelancermanagement.domain.models;

import org.springframework.lang.NonNull;
import mk.finki.ukim.emt.sharedkernel.domain.base.DomainObjectId;

public class FreelancerId extends DomainObjectId {
    private FreelancerId() {
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
