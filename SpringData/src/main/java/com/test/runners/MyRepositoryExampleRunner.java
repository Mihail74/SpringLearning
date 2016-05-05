package com.test.runners;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.test.repos.Customer;
import com.test.repos.MyRepository;

//@Component
public class MyRepositoryExampleRunner  implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(CRUDRepositoryExampleRunner.class);
	
	@Inject
	private MyRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		LOG.info("-------------------------START My Repository example--------------------------");
		// save a couple of customers
		repository.save(new Customer("Jack", "Bauer"));
		repository.save(new Customer("Chloe", "O'Brian"));
		repository.save(new Customer("Kim", "Bauer"));
		repository.save(new Customer("David", "Palmer"));
		repository.save(new Customer("Michelle", "Dessler"));

		// fetch all customers
		LOG.info("Customers found with findAll():");
		LOG.info("-------------------------------");
		for (Customer customer : repository.findAll()) {
			LOG.info(customer.toString());
		}
        LOG.info("");

		// fetch an individual customer by ID
		Customer customer = repository.findOne(1L);
		LOG.info("Customer found with findOne(1L):");
		LOG.info("--------------------------------");
		LOG.info(customer.toString());
        LOG.info("");

		// fetch customers by last name
		LOG.info("Customer found with findByLastName('Bauer'):");
		LOG.info("--------------------------------------------");
		for (Customer bauer : repository.findByLastName("Bauer")) {
			LOG.info(bauer.toString());
		}
        LOG.info("");
        
		LOG.info("Customer found with countByLastName('Bauer'):");
		LOG.info("--------------------------------------------");
		LOG.info("count=" + repository.countByLastName("Bauer"));
        LOG.info("");
        LOG.info("-------------------------END My Repository example--------------------------");
	}
}
