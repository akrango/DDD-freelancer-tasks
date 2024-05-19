package mk.finki.ukim.emt.freelancermanagement.config;


import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import mk.finki.ukim.emt.freelancermanagement.domain.models.Freelancer;
import mk.finki.ukim.emt.freelancermanagement.repository.FreelancerRepository;
import mk.finki.ukim.emt.sharedkernel.person.EmailAddress;
import mk.finki.ukim.emt.sharedkernel.person.PersonName;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInitializer {
    private final FreelancerRepository freelancerRepository;

    @PostConstruct
    public void initData(){
        if(freelancerRepository.findAll().isEmpty()){
            freelancerRepository.saveAndFlush(Freelancer.build(new PersonName("FreelancerName1", "FreelancerSurname1"), new EmailAddress("email1@gmail.com"), "Skills1"));
            freelancerRepository.saveAndFlush(Freelancer.build(new PersonName("FreelancerName2", "FreelancerSurname2"), new EmailAddress("email2@gmail.com"), "Skills1"));
            freelancerRepository.saveAndFlush(Freelancer.build(new PersonName("FreelancerName3", "FreelancerSurname3"), new EmailAddress("email3@gmail.com"), "Skills1"));
            freelancerRepository.saveAndFlush(Freelancer.build(new PersonName("FreelancerName4", "FreelancerSurname4"), new EmailAddress("email4@gmail.com"), "Skills1"));
            freelancerRepository.saveAndFlush(Freelancer.build(new PersonName("FreelancerName5", "FreelancerSurname5"), new EmailAddress("email5@gmail.com"), "Skills1"));
            freelancerRepository.saveAndFlush(Freelancer.build(new PersonName("FreelancerName6", "FreelancerSurname6"), new EmailAddress("email6@gmail.com"), "Skills1"));
        }

    }
}