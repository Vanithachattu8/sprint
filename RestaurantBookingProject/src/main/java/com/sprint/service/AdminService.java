package com.sprint.service;

import com.sprint.exceptions.BookingNotFoundException;
import com.sprint.exceptions.CustomerNotFoundException;
import com.sprint.exceptions.InvalidCredentialsException;
import com.sprint.exceptions.InvalidEmailPasswordException;
import com.sprint.exceptions.TransactionRecordNotFoundException;
import com.sprint.models.Admin;

public interface AdminService {
	
	public Admin registerAdmin(Admin admin) throws InvalidCredentialsException, InvalidEmailPasswordException;
	public String loginAdmin(String email,String password) throws InvalidCredentialsException;
	//public double discountsForCustomers(Long customerId)throws CustomerNotFoundException;
	public double calculateMoneySpent(long customerId)throws CustomerNotFoundException, BookingNotFoundException,TransactionRecordNotFoundException;
	
}