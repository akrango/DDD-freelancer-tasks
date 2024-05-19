package mk.finki.ukim.emt.taskmanagement.xport.events;

import lombok.AllArgsConstructor;
import mk.finki.ukim.emt.sharedkernel.domain.config.TopicHolder;
import mk.finki.ukim.emt.sharedkernel.domain.events.BidCreated;
import mk.finki.ukim.emt.sharedkernel.domain.events.BidRemoved;
import mk.finki.ukim.emt.sharedkernel.domain.events.DomainEvent;
import mk.finki.ukim.emt.taskmanagement.domain.model.BidId;
import mk.finki.ukim.emt.taskmanagement.domain.model.TaskId;
import mk.finki.ukim.emt.taskmanagement.domain.service.BidService;
import mk.finki.ukim.emt.taskmanagement.domain.service.TaskService;
import mk.finki.ukim.emt.taskmanagement.domain.valueobjects.Freelancer;
import mk.finki.ukim.emt.taskmanagement.domain.valueobjects.FreelancerId;
import mk.finki.ukim.emt.taskmanagement.xport.rest.FreelancerClient;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskEventListener {

    private final TaskService taskService;
    private final FreelancerClient freelancerClient;

    @KafkaListener(topics = TopicHolder.TOPIC_BID_CREATED, groupId = "task-management")
    public void consumeBidCreateEvent(String jsonMessage) {
        try {
            BidCreated event = DomainEvent.fromJson(jsonMessage, BidCreated.class);
            taskService.bidCreated(FreelancerId.of(event.getFreelancerId()), TaskId.of(event.getTaskId()), event.getProposal(), event.getAmount(), event.getTimestamp());
        } catch (Exception e) {

        }
    }

    @KafkaListener(topics = TopicHolder.TOPIC_BID_REMOVED, groupId = "task-management")
    public void consumeTaskRemovedEvent(String jsonMessage) {
        try {
            BidRemoved event = DomainEvent.fromJson(jsonMessage, BidRemoved.class);
            taskService.bidRemoved(BidId.of(event.getBidId()),FreelancerId.of(event.getFreelancerId()), TaskId.of(event.getTaskId()), event.getProposal(), event.getAmount(), event.getTimestamp());
        } catch (Exception e) {

        }
    }
}