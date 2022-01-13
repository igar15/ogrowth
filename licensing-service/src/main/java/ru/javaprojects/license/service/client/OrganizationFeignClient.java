package ru.javaprojects.license.service.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javaprojects.license.model.Organization;

@FeignClient("organization-service")
public interface OrganizationFeignClient {

    @RequestMapping(method= RequestMethod.GET,
                    value="/v1/organization/{organizationId}",
                    consumes="application/json")
    @CircuitBreaker(name = "organizationService")
    Organization getOrganization(@PathVariable("organizationId") String organizationId);
}
