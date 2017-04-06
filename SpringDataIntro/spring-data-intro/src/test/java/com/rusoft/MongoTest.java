package com.rusoft;

import com.rusoft.mongo.CrudEmployeeRepository;
import com.rusoft.mongo.Employee;
import com.rusoft.mongo.MongoConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MongoConfig.class})
public class MongoTest {

    private static final Logger logger = LoggerFactory.getLogger(MongoTest.class);

    @Autowired
    private CrudEmployeeRepository employees;

    @Test
    public void findAll() throws Exception {
        Iterable<Employee> all = employees.findAll();
        all.forEach(e -> logger.debug("{} - {} - {}", e.getFirstName(), e.getLastName(), e.getDateOfBirth()));
    }


}
