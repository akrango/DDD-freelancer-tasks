package mk.finki.ukim.emt.freelancermanagement.repository;

import mk.finki.ukim.emt.freelancermanagement.domain.models.Freelancer;
import mk.finki.ukim.emt.freelancermanagement.domain.models.FreelancerId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreelancerRepository extends JpaRepository<Freelancer, FreelancerId> {
}
