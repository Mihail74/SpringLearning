package com.test.repos.projections;


import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {

	Person findPersonByFirstName(String firstName);

	NoAddresses findByFirstName(String firstName);
	
	@Procedure("plus1inout")
	Integer explicitlyNamedPlus1inout(Integer arg);
}
