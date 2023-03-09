package com.sprint;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.sprint.dto.TransactionDTO;
import com.sprint.exceptions.CustomerNotFoundException;
import com.sprint.models.Admin;
import com.sprint.models.Booking;
import com.sprint.models.Customer;
import com.sprint.models.Transaction;
import com.sprint.repository.CustomerRepository;
import com.sprint.repository.TransactionRepository;
import com.sprint.service.TransactionImpl;
public class TransactionImplTests {
	@Mock
    private TransactionRepository transactionRepository;
	@Mock
	private CustomerRepository customerRepository;
	@InjectMocks
	private TransactionImpl transactionImpl;
	@BeforeEach 
	public void setup() {
     MockitoAnnotations.initMocks(this);
     }
	@Test
 public void testCreateRecord() {
 TransactionDTO transactionDTO = new TransactionDTO();
 transactionDTO.setTransactionId(1L);
 transactionDTO.setCost(1000.0);
 transactionDTO.setDate(LocalDate.now()); 
 Transaction transaction = new Transaction();
 transaction.setTransactionId(1L);
 transaction.setCost(1000.0);
 transaction.setDate(LocalDate.now());
 Admin admin = new Admin();
 admin.setAdminId(1);
 transaction.setAdmin(admin); Booking booking = new Booking();
 booking.setId(1L);
 transaction.setBooking(booking);
 when(transactionRepository.save(transaction)).thenReturn(transaction);
 when(customerRepository.findById(1L)).thenReturn(Optional.of(new Customer()));
 TransactionDTO result = transactionImpl.createRecord(1L, transactionDTO);
 assertEquals(1L, result.getTransactionId());
 assertEquals(1000.0, result.getCost());
 assertEquals(LocalDate.now(), result.getDate());
 }
	@Test
 public void testFindCustomerById() throws CustomerNotFoundException {
 Customer customer = new Customer();
 customer.setCustomerId(1L); when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
 Customer result = transactionImpl.findCustomerById(1L);
 assertEquals(1L, result.getCustomerId());
 }
	@Test
public void testGetTransactionsByDate() {
 LocalDate date = LocalDate.now();
 List<Transaction> transactions = new ArrayList<>();
 Transaction transaction = new Transaction();
 transaction.setTransactionId(1L);
 transaction.setCost(1000.0);
 transaction.setDate(date);
 transactions.add(transaction);
 when(transactionRepository.findByDate(date)).thenReturn(transactions);
 List<Transaction> result = transactionImpl.getTransactionsByDate(date);
 assertEquals(1, result.size());
 assertEquals(1L, result.get(0).getTransactionId());
 assertEquals(1000.0, result.get(0).getCost());
 assertEquals(date, result.get(0).getDate());
 }
}