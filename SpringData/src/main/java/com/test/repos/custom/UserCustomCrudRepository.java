package com.test.repos.custom;

import org.springframework.data.repository.CrudRepository;


public interface UserCustomCrudRepository extends CrudRepository<User2, Long>, UserRepositoryCustom {
}