package ru.javaprojects.license.service.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.javaprojects.license.model.Organization;

@Component
public class OrganizationRestTemplateClient {

    @Autowired
    private KeycloakRestTemplate restTemplate;

    @CircuitBreaker(name = "organizationService")
    public Organization getOrganization(String organizationId){
        ResponseEntity<Organization> restExchange =
                restTemplate.exchange(
                        "http://gateway:8072/organization-service/v1/organization/{organizationId}",
                        HttpMethod.GET,
                        null, Organization.class, organizationId);

        return restExchange.getBody();
    }
}
