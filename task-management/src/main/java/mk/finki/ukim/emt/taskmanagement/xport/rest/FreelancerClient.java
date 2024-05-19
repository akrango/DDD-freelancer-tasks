package mk.finki.ukim.emt.taskmanagement.xport.rest;

import mk.finki.ukim.emt.taskmanagement.domain.valueobjects.Freelancer;
import mk.finki.ukim.emt.taskmanagement.domain.valueobjects.FreelancerId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class FreelancerClient {
    private final RestTemplate restTemplate;
    private final String serverUrl;

    public FreelancerClient(@Value("${app.freelancer-management.url}") String serverUrl) {
        this.serverUrl = serverUrl;
        this.restTemplate = new RestTemplate();
        var requestFactory = new SimpleClientHttpRequestFactory();
        this.restTemplate.setRequestFactory(requestFactory);
    }

    private UriComponentsBuilder uri() {
        return UriComponentsBuilder.fromUriString(this.serverUrl);
    }

    public List<Freelancer> findAll() {
        try {
            return restTemplate.exchange(uri().path("/api/freelancer").build().toUri(), HttpMethod.GET,null, new ParameterizedTypeReference<List<Freelancer>>() {
            }).getBody();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public Optional<Freelancer> findById(FreelancerId freelancerId) {
        try {
            // Make a GET request to the endpoint with the freelancer ID
            ResponseEntity<Freelancer> response = restTemplate.exchange(uri().path("/api/freelancer/{id}").buildAndExpand(freelancerId).toUri(), HttpMethod.GET, null, Freelancer.class);

            // Check if the response is successful (status code 200 OK)
            if (response.getStatusCode() == HttpStatus.OK) {
                // Return the Freelancer object from the response body
                return Optional.ofNullable(response.getBody());
            } else {
                // If the response is not successful, return an empty Optional
                return Optional.empty();
            }
        } catch (Exception e) {
            // If an exception occurs, return an empty Optional
            return Optional.empty();
        }
    }

}
