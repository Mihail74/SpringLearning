package com.test.runners;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.test.repos.Customer;

//@Component
public class JdbcTemplateExample implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(JdbcTemplateExample.class);
	
	@Inject
	JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... args) throws Exception {
		LOG.info("-------------------------START JDBC TEMPLATE example--------------------------");
        LOG.info("Creating tables");

        jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE customers(" +
                "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");
        

        List<Object[]> splitUpNames =Arrays.asList(new Object[] {"Jhon", "Woo"},
        														 new Object[] {"Josh", "Bloch"},
        														 new Object[] {"Josh", "Long"});
        for (Object[] name : splitUpNames)
        {
        	LOG.info(String.format("Inserting customer record for %s %s", name[0], name[1]));
        }


        // Uses JdbcTemplate's batchUpdate operation to bulk load data
        jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);

        LOG.info("Querying for customer records where first_name = 'Josh':");
        List<Customer> query = jdbcTemplate.<Customer>query(
                "SELECT id, first_name, last_name FROM customers WHERE first_name = ?", new Object[] { "Josh" }, 
                new  RowMapper<Customer>() {
					@Override
					public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
						return new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"));
					}
				}
        );
        
        for (Customer customer : query)
        {
        	LOG.info(customer.toString());
        }
        LOG.info("-------------------------END JDBC TEMPLATE example--------------------------");
	}

}
