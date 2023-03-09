package com.sprint.repository;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sprint.models.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long>{

	@Query("Select u from Customer u where u.emailId=:email_id and u.password=:password")
	public List<Customer> validateUser(@Param(value="email_id")String emailId, @Param(value="password") String password);

	
}
