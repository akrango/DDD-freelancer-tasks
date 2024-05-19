package mk.finki.ukim.emt.freelancermanagement.service;

import mk.finki.ukim.emt.freelancermanagement.domain.models.Freelancer;
import mk.finki.ukim.emt.freelancermanagement.domain.models.FreelancerId;
import mk.finki.ukim.emt.freelancermanagement.service.forms.FreelancerForm;
import mk.finki.ukim.emt.sharedkernel.finance.Money;

import java.time.LocalDate;
import java.util.List;


public interface FreelancerService {

    Freelancer findById(FreelancerId freelancerId);

    Freelancer createFreelancer(FreelancerForm clientForm);

    List<Freelancer> findAll();

    Freelancer taskCreated(FreelancerId freelancerId, Money budget,
                           LocalDate deadline,
                           String title,
                           String description);

    Freelancer taskRemoved(FreelancerId freelancerId, Money budget,
                           LocalDate deadline,
                           String title,
                           String description);

}
