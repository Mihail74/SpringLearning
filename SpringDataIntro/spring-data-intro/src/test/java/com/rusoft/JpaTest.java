package com.rusoft;

import com.rusoft.jpa.config.DataConfig;
import com.rusoft.jpa.employee.CrudEmployeeRepository;
import com.rusoft.jpa.employee.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataConfig.class, InitConfig.class})
public class JpaTest {

    private static final Logger logger = LoggerFactory.getLogger(JpaTest.class);

    @Autowired
    private CrudEmployeeRepository employees;

    @Test
    public void findAll() throws Exception {
        Iterable<Employee> all = employees.findAll();
        all.forEach(e -> logger.debug("{} - {} - {}", e.getFirstName(), e.getLastName(), e.getDateOfBirth()));
    }



}
