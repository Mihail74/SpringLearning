package com.test.runners;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.test.repos.projections.PersonRepository;


//@Component
public class StoredProcedureExampleRunner implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(JdbcTemplateExample.class);
	
	@Inject
	JdbcTemplate jdbcTemplate;
	
	@Inject
	PersonRepository repository;

	@Override
	public void run(String... args) throws Exception {
		LOG.info("-------------------------START Stored procedure example--------------------------");
        LOG.info("Creating procedure");

        jdbcTemplate.execute("DROP procedure IF EXISTS plus1inout;");
        jdbcTemplate.execute("CREATE PROCEDURE plus1inout (IN arg int, OUT res int)"
        		+ "BEGIN ATOMIC  "
        		+ "set res = arg + 1; "
        		+ "END;");
        

        LOG.info("Stored procedure result explicitlyNamedPlus1inout(67)=" + repository.explicitlyNamedPlus1inout(67));
        LOG.info("");
        LOG.info("-------------------------END Stored procedure example--------------------------");
	}

}