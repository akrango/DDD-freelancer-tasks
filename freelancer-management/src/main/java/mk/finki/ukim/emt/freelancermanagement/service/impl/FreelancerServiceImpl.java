package mk.finki.ukim.emt.freelancermanagement.service.impl;

import mk.finki.ukim.emt.freelancermanagement.domain.models.Freelancer;
import mk.finki.ukim.emt.freelancermanagement.domain.models.FreelancerId;
import mk.finki.ukim.emt.freelancermanagement.exceptions.FreelanceNotFoundException;
import mk.finki.ukim.emt.freelancermanagement.repository.FreelancerRepository;
import mk.finki.ukim.emt.freelancermanagement.service.FreelancerService;
import mk.finki.ukim.emt.freelancermanagement.service.forms.FreelancerForm;
import mk.finki.ukim.emt.sharedkernel.finance.Money;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FreelancerServiceImpl implements FreelancerService {

    private final FreelancerRepository freelancerRepository;

    public FreelancerServiceImpl(FreelancerRepository freelancerRepository) {
        this.freelancerRepository = freelancerRepository;
    }

    @Override
    public Freelancer findById(FreelancerId freelancerId) {
        return freelancerRepository.findById(freelancerId).orElseThrow(FreelanceNotFoundException::new);
    }

    @Override
    public Freelancer createFreelancer(FreelancerForm freelancerForm) {
        Freelancer freelancer=Freelancer.build(freelancerForm.getFreelancerName(), freelancerForm.getEmailAddress(), freelancerForm.getSkills());
        this.freelancerRepository.save(freelancer);
        return freelancer;
    }

    @Override
    public List<Freelancer> findAll() {
        return freelancerRepository.findAll();
    }

    @Override
    public Freelancer taskCreated(FreelancerId freelancerId, Money budget, LocalDate deadline, String title, String description) {
        return null;
    }

    @Override
    public Freelancer taskRemoved(FreelancerId freelancerId, Money budget, LocalDate deadline, String title, String description) {
        return null;
    }
}
