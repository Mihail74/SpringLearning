package com.test.runners;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.test.repos.Customer;
import com.test.repos.CustomerRepository;
import com.test.repos.User;
import com.test.repos.UserRepository;

//@Component
public class PagingRepositoryExampleRunner  implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(CRUDRepositoryExampleRunner.class);
	
	@Inject
	private UserRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		LOG.info("-------------------------START Paging Repository example--------------------------");
		// save a couple of customers
		repository.save(new User("Jack", "Bauer"));
		repository.save(new User("Jack", "Bauer2"));
		repository.save(new User("Jack", "Bauer3"));
		repository.save(new User("Chloe", "O'Brian"));
		repository.save(new User("Kim", "Bauer"));
		repository.save(new User("David", "Palmer"));
		repository.save(new User("Michelle", "Dessler"));

		LOG.info("Users found with findAll() 2 page by 2 entity:");
		LOG.info("-------------------------------");
		for (User user : repository.findAll(new PageRequest(1, 2))) {
			LOG.info(user.toString());
		}
        LOG.info("");

		LOG.info("Users found with findByFirstName() 3 page by 2 entity:");
		LOG.info("-------------------------------");
		for (User user : repository.findByFirstName("Jack", (new PageRequest(2, 2)))) {
			LOG.info(user.toString());
		}
        LOG.info("");

		LOG.info("Users found with findAll() 1 page by 3 entity:");
		LOG.info("-------------------------------");
		for (User user : repository.findAll(new PageRequest(0, 3))) {
			LOG.info(user.toString());
		}
        LOG.info("");
        
		LOG.info("Users found with findAll() 2 page by 3 entity:");
		LOG.info("-------------------------------");
		for (User user : repository.findAll(new PageRequest(1, 3))) {
			LOG.info(user.toString());
		}
        LOG.info("");
        
        LOG.info("-------------------------END Paging Repository example--------------------------");
	}
}