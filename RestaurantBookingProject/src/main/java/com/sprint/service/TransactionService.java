package com.sprint.service;

import java.time.LocalDate;
import java.util.List;

import com.sprint.dto.TransactionDTO;

import com.sprint.exceptions.CustomerNotFoundException;
import com.sprint.models.Customer;
import com.sprint.models.Transaction;

public interface TransactionService {
	//public Transaction getTransactionByBookingId(Long bookingId)throws TransactionRecordNotFoundException;

	public List<Transaction> getAllTransactions();
	public TransactionDTO createRecord(long id,TransactionDTO transaction);
	public Customer findCustomerById(long customerId) throws CustomerNotFoundException ;
	public List<Transaction> getTransactionsByDate(LocalDate ld);
	}
