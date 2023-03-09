package com.sprint.service;

import java.util.List;
import com.sprint.dto.CustomerDTO;
import com.sprint.exceptions.CustomerAlreadyExistsException;
import com.sprint.exceptions.CustomerNotFoundException;
import com.sprint.exceptions.InvalidCredentialsException;
import com.sprint.models.Customer;

public interface CustomerService {
	public CustomerDTO registerCustomer(CustomerDTO customer)throws CustomerAlreadyExistsException;
	public List<Customer> findCustomersByFrequencyOfVisits();
	public Customer findCustomerById(long id) throws CustomerNotFoundException;
	public List<Customer> getCustomers();
	public String loginCustomer(String email,String password)throws InvalidCredentialsException;
}