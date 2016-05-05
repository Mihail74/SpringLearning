package com.test.repos;

import java.util.List;

import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass=Customer.class, idClass=Long.class)
public interface MyRepository {
    List<Customer> findByLastName(String lastName);

    Long countByLastName(String lastName);
    
    Customer findOne(Long id);
    
    Iterable<Customer> findAll();
    
    Customer save(Customer entity);
}
