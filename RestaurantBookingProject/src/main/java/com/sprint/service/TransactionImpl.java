package com.sprint.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.dto.TransactionDTO;
import com.sprint.exceptions.CustomerNotFoundException;
import com.sprint.models.Admin;
import com.sprint.models.Booking;
import com.sprint.models.Customer;
import com.sprint.models.Transaction;
import com.sprint.repository.CustomerRepository;
import com.sprint.repository.TransactionRepository;

@Service
public class TransactionImpl implements TransactionService{

	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private CustomerRepository customerRepository;
	
	private Admin admin=new Admin();
	private Booking booking=new Booking();
	
	
	public TransactionDTO createRecord(long id,TransactionDTO transaction) {
	Transaction trans=new Transaction();
	
	trans.setTransactionId(transaction.getTransactionId());
	trans.setCost(transaction.getCost());
	trans.setDate(transaction.getDate());
	trans.setPaymentMode(transaction.getPaymentMode()); 
	admin.setAdminId(1);
	trans.setAdmin(admin);
	booking.setId(id);
	trans.setBooking(booking);
	Transaction t=transactionRepository.save(trans);
	
	transaction.setTransactionId(trans.getTransactionId());
	return transaction;
	
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
	public List<Transaction> getTransactionsByDate(LocalDate date) {
        return transactionRepository.findByDate(date);
    }

	public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
	
	

}
