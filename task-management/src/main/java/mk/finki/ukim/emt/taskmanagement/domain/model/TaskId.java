package mk.finki.ukim.emt.taskmanagement.domain.model;

import lombok.NonNull;
import mk.finki.ukim.emt.sharedkernel.domain.base.DomainObjectId;
import mk.finki.ukim.emt.taskmanagement.domain.valueobjects.FreelancerId;

public class TaskId extends DomainObjectId {
    private TaskId() {
        super(TaskId.randomId(TaskId.class).getId());
    }

    public TaskId(@NonNull String uuid) {
        super(uuid);
    }

    public static TaskId of(String uuid) {
        TaskId p = new TaskId(uuid);
        return p;
    }
}
