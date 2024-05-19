package mk.finki.ukim.emt.taskmanagement.domain.service.impl;

import lombok.AllArgsConstructor;

import lombok.NonNull;
import mk.finki.ukim.emt.sharedkernel.domain.events.BidCreated;
import mk.finki.ukim.emt.taskmanagement.domain.model.Bid;
import mk.finki.ukim.emt.taskmanagement.domain.model.BidId;
import mk.finki.ukim.emt.taskmanagement.domain.repository.BidRepository;
import mk.finki.ukim.emt.taskmanagement.domain.service.BidService;
import mk.finki.ukim.emt.taskmanagement.domain.service.forms.BidForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import mk.finki.ukim.emt.sharedkernel.domain.infra.DomainEventPublisher;



@Service
@Transactional
@AllArgsConstructor
public class BidServiceImpl implements BidService {

    private final BidRepository bidRepository;
    private final Validator validator;
    private final DomainEventPublisher domainEventPublisher;


    @Override
    public BidId createBid(BidForm bidForm) {
        Objects.requireNonNull(bidForm, "bid must not be null");
        var constraintValidation = validator.validate(bidForm);
        if (constraintValidation.size()>0){
            throw new ConstraintViolationException("the bid form is not valid", constraintValidation);
        }
        var newBid=bidRepository.saveAndFlush(toDomainObject(bidForm));
        //String freelancerId, String taskId, String proposal, Money amount, LocalDate timestamp
        //domainEventPublisher.publish(new BidCreated(newBid.getFreelancerId().getId(), newBid.getTaskId().getId(), newBid.getProposal(), newBid.getAmount(), newBid.getTimestamp()));
        return newBid.getId();
    }

    private Bid toDomainObject(BidForm bidForm){
        var bid = new Bid(bidForm.getTask().getId(), bidForm.getFreelancer().getId(), bidForm.getProposal(), bidForm.getAmount(), bidForm.getTimestamp());
        return bid;
    }

    @Override
    public List<Bid> findAll() {
        return bidRepository.findAll();
    }

    @Override
    public Optional<Bid> findById(BidId bidId) {
        return bidRepository.findById(bidId);
    }
}
