package ru.javaprojects.organizationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javaprojects.organizationservice.model.Organization;
import ru.javaprojects.organizationservice.service.OrganizationService;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping(value="v1/organization")
public class OrganizationController {
    @Autowired
    private OrganizationService service;


    @RolesAllowed({ "ADMIN", "USER" })
    @GetMapping("/{organizationId}")
    public ResponseEntity<Organization> getOrganization(@PathVariable("organizationId") String organizationId) {
        return ResponseEntity.ok(service.findById(organizationId));
    }

    @RolesAllowed({ "ADMIN", "USER" })
    @PutMapping("/{organizationId}")
    public void updateOrganization( @PathVariable("organizationId") String id, @RequestBody Organization organization) {
        service.update(organization);
    }

    @RolesAllowed({ "ADMIN", "USER" })
    @PostMapping
    public ResponseEntity<Organization>  saveOrganization(@RequestBody Organization organization) {
        return ResponseEntity.ok(service.create(organization));
    }

    @RolesAllowed("ADMIN")
    @DeleteMapping("/{organizationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganization( @PathVariable("organizationId") String organizationId) {
        service.delete(organizationId);
    }
}
