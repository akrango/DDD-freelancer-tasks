package mk.finki.ukim.emt.taskmanagement.xport.rest;

import mk.finki.ukim.emt.taskmanagement.domain.valueobjects.Freelancer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

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
}
