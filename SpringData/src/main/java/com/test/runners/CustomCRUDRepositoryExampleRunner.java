package com.test.runners;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.test.repos.custom.User2;
import com.test.repos.custom.UserCustomCrudRepository;

//@Component
public class CustomCRUDRepositoryExampleRunner  implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(CRUDRepositoryExampleRunner.class);
	
	@Inject
	private UserCustomCrudRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		LOG.info("-------------------------START CUSTOM CRUD Repository example--------------------------");
		// save a couple of customers
		repository.save(new User2("Jack", "Bauer"));
		repository.save(new User2("Chloe", "O'Brian"));
		repository.save(new User2("Chloe", "O'Brian2"));
		repository.save(new User2("Kim", "Bauer"));
		repository.save(new User2("Kimber", "Bauer"));
		repository.save(new User2("David", "Palmer"));
		repository.save(new User2("Michelle", "Dessler"));

		// fetch all customers
		LOG.info("User found with findAll():");
		LOG.info("-------------------------------");
		for (User2 user : repository.findAll()) {
			LOG.info(user.toString());
		}
        LOG.info("");
        
        LOG.info("Customers found with someCustomMethod(Chloe):");
        LOG.info("-------------------------------");
        repository.someCustomMethod("Chloe");
        LOG.info("");

        LOG.info("-------------------------END CUSTOM CRUD Repository example--------------------------");
	}
}
