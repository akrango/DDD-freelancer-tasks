package mk.finki.ukim.emt.taskmanagement.domain.repository;

import mk.finki.ukim.emt.taskmanagement.domain.model.Bid;
import mk.finki.ukim.emt.taskmanagement.domain.model.BidId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidRepository extends JpaRepository<Bid, BidId> {
}
