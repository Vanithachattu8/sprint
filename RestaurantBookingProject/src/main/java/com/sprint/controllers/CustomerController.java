package com.sprint.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.dto.BookingDTO;
import com.sprint.dto.CustomerDTO;
import com.sprint.dto.TransactionDTO;
import com.sprint.exceptions.BookingAlreadyExistsException;
import com.sprint.exceptions.BookingNotFoundException;
import com.sprint.exceptions.CustomerAlreadyExistsException;
import com.sprint.exceptions.CustomerNotFoundException;
import com.sprint.exceptions.InvalidCredentialsException;
import com.sprint.exceptions.TableNotFoundException;
import com.sprint.models.Booking;
import com.sprint.models.Customer;
import com.sprint.models.Restaurant;
import com.sprint.repository.CustomerRepository;
import com.sprint.service.BookingService;
import com.sprint.service.CustomerService;
import com.sprint.service.RestaurantService;
import com.sprint.service.TransactionService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private BookingService bookingService;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	   TransactionService transactionService;
	
	@Autowired
	RestaurantService restaurantService;
	  
		@Autowired
	  public CustomerController(BookingService bookingService) {
	    this.bookingService = bookingService;
	  }
		
		@PostMapping("/register")
		public ResponseEntity<CustomerDTO> registerCustomer(@RequestBody CustomerDTO customer)throws CustomerAlreadyExistsException {
			CustomerDTO usr= this.customerService.registerCustomer(customer);
			return new ResponseEntity<>(usr, HttpStatus.CREATED);
		}
		
		@PostMapping("/loginCustomer/{email}/{password}")
		public String loginCustomer(@PathVariable("email") String email,@PathVariable("password")String password) throws InvalidCredentialsException{
			return this.customerService.loginCustomer(email,password);
			}
	
		
		@PostMapping(value="/book/{custId}/{time}")
		public  ResponseEntity<String> bookTable(@PathVariable("custId") long custId,@PathVariable("time") String time,@RequestBody BookingDTO booking) throws BookingAlreadyExistsException
		{
			LocalTime t=LocalTime.parse(time);
			BookingDTO b=bookingService.bookTable(custId,t,booking);
			String message = "Table " + b.getTableNumber() + " has been booked for " + b.getDate() + " at " + t + " for " + b.getNumberOfGuests() + " guests by customer with ID " + custId + " and Booking ID: " + b.getId();
            return ResponseEntity.ok(message);
		}
		 //to post transaction
		@PostMapping(value="/proceedToPay/{bookingId}")
		public ResponseEntity<String> payForBooking(@PathVariable("bookingId")long bookingId,@RequestBody TransactionDTO transaction)
		{
			TransactionDTO tr=this.transactionService.createRecord(bookingId,transaction);
			String message = "Transaction successful for this booking Id " + bookingId + " & transaction Id " + tr.getTransactionId() +" payment mode is " + tr.getPaymentMode() + " on " + tr.getDate();
			return ResponseEntity.ok(message);
		}
		  
	  
	  @PutMapping(value="/bookings/{bookingId}/{date}/{time}")
	  public ResponseEntity<String> updateBooking(@PathVariable("bookingId") Long bookingId, 
			  @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate newDate, 
			  @PathVariable("time") String newTime) throws BookingNotFoundException{
		  LocalTime t = LocalTime.parse(newTime); 
		  Booking updatedBooking = bookingService.updateBooking(bookingId, newDate, t);
		  String message = "Booking ID " + updatedBooking.getId() + " has been updated with new date: " + updatedBooking.getDate() + " and time: " + t;
          return ResponseEntity.ok(message);
	  }
	  
	  @DeleteMapping(value="/bookings/{bookingId}")
	  public ResponseEntity<String> cancelBooking(@PathVariable("bookingId") Long bookingId)throws BookingNotFoundException {
	    
			bookingService.cancelBooking(bookingId);
			 return ResponseEntity.ok("Booking with ID " + bookingId + " has been cancelled.");
	  }
	  @GetMapping(value="/{id}")
	  public Customer getCustomerById(@PathVariable("id") Long id) throws CustomerNotFoundException{
        return customerService.findCustomerById(id);
    } 
	  
	  @GetMapping
	  public List<Customer> getAllCustomers(){
        return customerService.getCustomers();
    } 
	  
	  @GetMapping(value="/getAllTables")
	  public List<Restaurant> getAllTables()
	  {
		  return restaurantService.getAllAvailableTables();
	  }
	  
	  @GetMapping(value="/getTablesBySeatingCapacity/{seatingCapacity}")
	  public List<Restaurant> getTablesBySeating(@PathVariable("seatingCapacity") int seatingCapacity) throws TableNotFoundException
	  {
		  return restaurantService.getTablesBySeating(seatingCapacity);
	  }
	  
	  @GetMapping(value="/getTablesByDateTime/{date}/{startTime}/{endTime}")
	  public List<Restaurant> getTablesAvailableOnDateTime(@PathVariable("date")@DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date,@PathVariable("startTime")String startTime,@PathVariable("endTime")String endTime) throws TableNotFoundException
	  {
		  LocalTime t=LocalTime.parse(startTime);
			LocalTime t1=LocalTime.parse(endTime);
		  return restaurantService.getTablesAvailableOnDateTime(date, t,t1);
	  }
}
