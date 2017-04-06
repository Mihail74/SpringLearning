package com.rusoft.jpa.employee;

import org.springframework.data.repository.CrudRepository;

public interface CrudEmployeeRepository extends CrudRepository<Employee, Integer> {

}
