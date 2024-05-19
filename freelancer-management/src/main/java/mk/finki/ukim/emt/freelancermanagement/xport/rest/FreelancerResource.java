package mk.finki.ukim.emt.freelancermanagement.xport.rest;

import lombok.AllArgsConstructor;
import mk.finki.ukim.emt.freelancermanagement.domain.models.Freelancer;
import mk.finki.ukim.emt.freelancermanagement.service.FreelancerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/freelancer")
@AllArgsConstructor
public class FreelancerResource {
    private final FreelancerService freelancerService;

    @GetMapping
    public List<Freelancer> getAll() {
        return freelancerService.findAll();
    }

}