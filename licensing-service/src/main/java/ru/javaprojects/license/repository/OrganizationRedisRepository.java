package ru.javaprojects.license.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.javaprojects.license.model.Organization;

@Repository
public interface OrganizationRedisRepository extends CrudRepository<Organization, String> {
}
