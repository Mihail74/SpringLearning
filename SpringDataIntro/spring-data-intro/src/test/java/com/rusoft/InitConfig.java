package com.rusoft;

import com.rusoft.jpa.employee.CrudEmployeeRepository;
import com.rusoft.jpa.employee.Employee;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Arrays;

@Configuration
public class InitConfig {

    @Autowired
    private CrudEmployeeRepository employees;

    @Bean
    InitializingBean init() {
        return () -> {
            employees.save(Arrays.asList(
                    new Employee("Ivan", "Ivanov", LocalDate.of(1950, 1,1)),
                    new Employee("Petr", "Petrov", LocalDate.of(1960, 2,2)),
                    new Employee("Nikolay", "Sidorov", LocalDate.of(1970, 3,3)),
                    new Employee("Vasiliy", "Vasiliev", LocalDate.of(1980, 4,4)),
                    new Employee("Anton", "Antonov", LocalDate.of(1990, 5,5)),
                    new Employee("Ivan", "Petrov", LocalDate.of(1965, 2,2)),
                    new Employee("Ivan", "Sidorov", LocalDate.of(1975, 3,3))
            ));
        };
    }

}
