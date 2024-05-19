package mk.finki.ukim.emt.taskmanagement.domain.model;

import lombok.NonNull;
import mk.finki.ukim.emt.sharedkernel.domain.base.DomainObjectId;
import mk.finki.ukim.emt.taskmanagement.domain.valueobjects.FreelancerId;

public class BidId extends DomainObjectId {
    private BidId() {
        super(BidId.randomId(BidId.class).getId());
    }

    public BidId(@NonNull String uuid) {
        super(uuid);
    }
    public static BidId of(String uuid) {
        BidId p = new BidId(uuid);
        return p;
    }
}
