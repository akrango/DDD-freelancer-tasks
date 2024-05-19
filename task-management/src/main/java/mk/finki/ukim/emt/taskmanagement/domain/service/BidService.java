package mk.finki.ukim.emt.taskmanagement.domain.service;

import mk.finki.ukim.emt.taskmanagement.domain.model.Bid;
import mk.finki.ukim.emt.taskmanagement.domain.model.BidId;
import mk.finki.ukim.emt.taskmanagement.domain.service.forms.BidForm;

import java.util.List;
import java.util.Optional;

public interface BidService {
    BidId createBid(BidForm bidForm);
    List<Bid> findAll();
    Optional<Bid> findById(BidId bidId);
}
