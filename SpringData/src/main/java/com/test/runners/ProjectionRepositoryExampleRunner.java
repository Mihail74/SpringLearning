package com.test.runners;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.test.repos.projections.Address;
import com.test.repos.projections.AddressRepository;
import com.test.repos.projections.NoAddresses;
import com.test.repos.projections.Person;
import com.test.repos.projections.PersonRepository;

//@Component
public class ProjectionRepositoryExampleRunner  implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(ProjectionRepositoryExampleRunner.class);
	
	@Inject
	private PersonRepository repository;
	@Inject
	private AddressRepository addrRepository;
	
	@Override
	public void run(String... args) throws Exception {
		LOG.info("-------------------------START Projection Repository example--------------------------");
		// save a couple of customers
		Address address1 = new Address("street1", "state1", "country1");
		Address address2 = new Address("street2", "state2", "country2");
		Address address3 = new Address("street3", "state3", "country3");
		Address address4 = new Address("street4", "state4", "country4");
		addrRepository.save(address1);
		addrRepository.save(address2);
		addrRepository.save(address3);
		addrRepository.save(address4);
		
		repository.save(new Person("Jack", "Bauer", address1));
		repository.save(new Person("Jack1", "Bauer", address2));
		repository.save(new Person("Jack2", "Bauer", address3));
		repository.save(new Person("Jack3", "Bauer", address4));


		// fetch all customers
		LOG.info("Person NoAddresses found with findByFirstName(Jack1):");
		LOG.info("-------------------------------");
		NoAddresses findByFirstName = repository.findByFirstName("Jack1");
//		LOG.info("firstName=" + findByFirstName.getFirstName() + "; lastName=" +  findByFirstName.getName() + "; country=" + findByFirstName.getCountry());
        LOG.info("");

        LOG.info("-------------------------END Projection Repository example--------------------------");
	}
}
