package com.sprint.service;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.exceptions.CustomerNotFoundException;
import com.sprint.exceptions.InvalidCredentialsException;
import com.sprint.exceptions.InvalidEmailPasswordException;
import com.sprint.models.Admin;
import com.sprint.models.Customer;
import com.sprint.repository.AdminRepository;
import com.sprint.repository.TransactionRepository;

@Service
public class AdminImpl implements AdminService{
	  
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
//	// Email regex pattern
//	private static final String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
//	 
//	 // Password regex pattern
//	private static final String regex1 ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}";
//
//	public Admin registerAdmin(Admin admin) throws InvalidEmailPasswordException {
//		Admin a=null;
//		if(!(Pattern.matches(regex, admin.getEmailId())))
//		{
//			throw new InvalidEmailPasswordException();
//		}
//		else if(!(Pattern.matches(regex1, admin.getPassword())))
//		{
//			throw new InvalidEmailPasswordException();
//		}
//		a= adminRepository.save(admin);
//		return a;
//	}
	public Admin registerAdmin(Admin admin) {
		return adminRepository.save(admin);
	}
	
	public String loginAdmin(String email,String password) throws InvalidCredentialsException{
		if(adminRepository.validateAdmin(email,password)==null) {
			throw new InvalidCredentialsException("Credentials given are Invalid!");
			}
		return "Login Successful";
		}
	
	@Override
	public double calculateMoneySpent(long customerId) throws CustomerNotFoundException {
		Customer existingCustomer =transactionService.findCustomerById(customerId);
		return transactionRepository.findTotalCostSpent(customerId);
	}	
	
}

