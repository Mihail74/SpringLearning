package com.test.repos.custom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class UserCustomCrudRepositoryImpl implements UserRepositoryCustom {

	private static final Logger LOG = LoggerFactory.getLogger(UserCustomCrudRepositoryImpl.class);
	
	@Inject
	JdbcTemplate jdbcTemplate;
	
	  public void someCustomMethod(String firstname) {
	        List<User2> query = jdbcTemplate.<User2>query(
	                "SELECT id, first_name, last_name FROM user2 WHERE first_name = ?", new Object[] { firstname }, 
	                new  RowMapper<User2>() {
						@Override
						public User2 mapRow(ResultSet rs, int rowNum) throws SQLException {
							return new User2(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"));
						}
					}
	        );
	        for (User2 user : query)
	        {
	        	LOG.info(user.toString());
	        }
	 }
}
