package mk.finki.ukim.emt.freelancermanagement.xport.events;

import lombok.AllArgsConstructor;
import mk.finki.ukim.emt.freelancermanagement.domain.models.FreelancerId;
import mk.finki.ukim.emt.freelancermanagement.service.FreelancerService;
import mk.finki.ukim.emt.sharedkernel.domain.config.TopicHolder;
import mk.finki.ukim.emt.sharedkernel.domain.events.DomainEvent;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//
//@Service
//@AllArgsConstructor
//public class FreelancerEventListener {
//
//    private final FreelancerService freelancerService;
//
//    @KafkaListener(topics = TopicHolder.TOPIC_ORDER_ITEM_CREATED, groupId = "freelancer-manager")
//    public void consumeTaskCreateEvent(String jsonMessage){
//        try{
//            TaskCreated event= DomainEvent.fromJson(jsonMessage, TaskCreated.class);
//            freelancerService.taskCreated(FreelancerId.of(event.getFreelancerId()), event.getBudget(), event.getDeadline(), event.getTitle(), event.getDescription());
//        }catch (Exception e){
//
//        }
//    }
//
//    @KafkaListener(topics = TopicHolder.TOPIC_ORDER_ITEM_CREATED, groupId = "freelancer-manager")
//    public void consumeTaskRemovedEvent(String jsonMessage){
//        try{
//            TaskRemoved event= DomainEvent.fromJson(jsonMessage, TaskRemoved.class);
//            freelancerService.taskRemoved(FreelancerId.of(event.getFreelancerId()), event.getBudget(), event.getDeadline(), event.getTitle(), event.getDescription());
//        }catch (Exception e){
//
//        }
//    }
//}
