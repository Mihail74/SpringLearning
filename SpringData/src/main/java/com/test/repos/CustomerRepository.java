package com.test.repos;

import java.util.List;
import java.util.concurrent.Future;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

	@Async
	Future<List<Customer>> findByFirstName(String firstname); 
	
    List<Customer> findByCustomerName(String firstname);
    
    @Query("select c from Customer c where c.lastName = :paramName")
    List<Customer> findByQueryLastName(@Param("paramName") String lastName);
    
    List<Customer> findByLastName(String lastName);
    
    List<Customer> findTop2ByLastName(String lastName);
    List<Customer> findFirst2ByLastName(String lastName);
    
    @Query("select c from Customer c")
    List<Customer> doCustomQuerySelectAll();

    Long countByLastName(String lastName);
}