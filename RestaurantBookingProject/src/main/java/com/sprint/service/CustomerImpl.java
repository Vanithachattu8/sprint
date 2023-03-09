package com.sprint.service;

import java.util.Comparator;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.dto.CustomerDTO;
import com.sprint.exceptions.CustomerAlreadyExistsException;
import com.sprint.exceptions.CustomerNotFoundException;
import com.sprint.exceptions.InvalidCredentialsException;
import com.sprint.models.Booking;
import com.sprint.models.Customer;
import com.sprint.repository.BookingRepository;
import com.sprint.repository.CustomerRepository;


@Service
public class CustomerImpl implements CustomerService{
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	 @Autowired
	  public CustomerImpl(BookingRepository bookingRepository) 
	 {
		  this.bookingRepository = bookingRepository;
	 }
	 
	 @Override
		public CustomerDTO registerCustomer(CustomerDTO customer) throws CustomerAlreadyExistsException{
		 Customer cust=new Customer();
		 cust.setCustomerId(customer.getCustomerId());
		 if(customerRepository.existsById(cust.getCustomerId()))
		 {
			 throw new CustomerAlreadyExistsException("Customer already registered with given Id");
		 }
		 cust.setCustomerName(customer.getCustomerName());
		 cust.setPhoneNo(customer.getPhoneNo());
		 cust.setEmailId(customer.getEmailId());
		 cust.setPassword(customer.getPassword());
		 Customer c=customerRepository.save(cust);
		 customer.setCustomerId(cust.getCustomerId());
		  return customer;
		}
	 
	 @Override
	 public String loginCustomer(String email,String password) throws InvalidCredentialsException {
		 if(customerRepository.validateUser(email,password).isEmpty())
		 {
			 throw new InvalidCredentialsException("Credentials given are Invalid!");
		}
	 return "Login Successful";
	 }


	@Override
	public List<Customer> findCustomersByFrequencyOfVisits() {
		List<Booking> bookings = bookingRepository.findAll(); 
		  Map<Long, Integer> customerVisitCounts = new HashMap<>();
		  for (Booking booking : bookings) { 
			  Long customerId = booking.getId();
			  customerVisitCounts.put(customerId, customerVisitCounts.getOrDefault(customerId, 0) + 1);
			  } 
		 return customerVisitCounts.entrySet().stream() 
				 .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) 
					  .map(entry -> bookingRepository.findById(entry.getKey()).get().getCustomer())
					  .collect(Collectors.toList()); 
	}


	public Customer findCustomerById(long customerId) throws CustomerNotFoundException {
		Customer customer;
		if(customerRepository.findById(customerId).isEmpty()) {
			throw new CustomerNotFoundException("Customer with given Id is not present");
		}
		else {
			customer=customerRepository.findById(customerId).get();
		}
		return customer;
	}

	@Override
	public List<Customer> getCustomers() {
		Iterable<Customer> custList=customerRepository.findAll();
		return (List<Customer>) custList;
	}

}
