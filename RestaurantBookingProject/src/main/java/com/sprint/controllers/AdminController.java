package com.sprint.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.exceptions.BookingNotFoundException;
import com.sprint.exceptions.CustomerNotFoundException;
import com.sprint.exceptions.InvalidCredentialsException;
import com.sprint.exceptions.InvalidEmailPasswordException;
import com.sprint.exceptions.TransactionRecordNotFoundException;
import com.sprint.models.Admin;
import com.sprint.models.Booking;
import com.sprint.models.Customer;
import com.sprint.models.Restaurant;
import com.sprint.models.Transaction;
import com.sprint.repository.TransactionRepository;
import com.sprint.service.AdminImpl;
import com.sprint.service.BookingImpl;
import com.sprint.service.CustomerImpl;
import com.sprint.service.RestaurantService;
import com.sprint.service.TransactionService;



@RestController
@RequestMapping("/admin")
public class AdminController {
	  
	  @Autowired
	  private TransactionRepository transactionRepository;
	  
	  
	  @Autowired
	  AdminImpl adminImpl;
	  @Autowired
	  private CustomerImpl customerImpl;
	  
	  @Autowired
	   private BookingImpl bookingImpl;
	 
	  @Autowired
	   TransactionService transactionService;
	  
	  @Autowired
	  RestaurantService restaurantService;
	  
	   //admin registration
	   @PostMapping("/register")
		public ResponseEntity<String> registerAdmin(@RequestBody Admin admin) throws InvalidEmailPasswordException {
		   Admin f=this.adminImpl.registerAdmin(admin);
		   String message="Admin has successfully registered with adminId: "+f.getAdminId()+" as "+f.getAdminName()+" with "+f.getEmailId();
		   return ResponseEntity.ok(message);
		}
	   
	   //admin login
	   @PostMapping("/loginAdmin/{email}/{password}")
	   public String loginAdmin(@PathVariable("email") String email,@PathVariable("password")String password) throws InvalidCredentialsException{
		   return this.adminImpl.loginAdmin(email,password);
		   }
	
	   
	// Display a list of customers ordered by frequency of visits
	   @GetMapping(value="/customers/visits")
	   public List<Customer> getCustomersByFrequencyOfVisits(Model model) {
	      List<Customer> customers = customerImpl.findCustomersByFrequencyOfVisits();

	      model.addAttribute("customers", customers);

	      return customers;
	   }
	      
	   // display list of bookings for a given  date
	      @GetMapping(value="/customers/{date}")
	      public List<Booking> getBookings(@PathVariable("date")@DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date,
	    		  Model model) throws BookingNotFoundException{
	       List<Booking> bookings = bookingImpl.findBookingByDate(date);

	         model.addAttribute("bookings", bookings);

	         return bookings;
	      
	   }
	      @GetMapping("/table/{tableNumber}/{date}")
		  public ResponseEntity<List<String>> getBookingsForTable(@PathVariable int tableNumber, @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) throws BookingNotFoundException {
		      List<String> bookingDetails = bookingImpl.displayTableBookings(tableNumber, date);
		      if (bookingDetails.isEmpty()) {
		          throw new BookingNotFoundException("No bookings found for table number " + tableNumber + " and date " + date);
		      }
		      return ResponseEntity.ok(bookingDetails);
		  }
	  //amount spent between dates
	  @GetMapping(value="/amountSpentOnDate/{id}/{endDate}/{startDate}")
	  public Double getTotalAmountSpentByCustomerIdBetweenDates(@PathVariable("id") Long customerId,
			  @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
			  @PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate)
	  {
	    return transactionRepository.findTotalAmountSpentByCustomerIdBetweenDates(customerId, endDate,startDate);
	  }
	  
	  
	  //total money spent
	  @GetMapping(value="/{id}")
	  public Double getCalculateMoneySpent(@PathVariable("id") Long customerId) throws CustomerNotFoundException
	  {
		  return adminImpl.calculateMoneySpent(customerId);
	  }
	  
	  @PostMapping(value="/restaurantTables")
	  public ResponseEntity<Restaurant> arrangeTables(@RequestBody Restaurant restaurant)
	  {
		  Restaurant res=this.restaurantService.createRestaurant(restaurant);
		  return new ResponseEntity<Restaurant>(res,HttpStatus.CREATED);
	  }
	  @GetMapping("/date/{date}")
	    public List<Transaction> getTransactionsByDate(@PathVariable String date) {
	        LocalDate ld = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	        return transactionService.getTransactionsByDate(ld);
	    }
	  
	  @GetMapping("/transactions/{customerId}")
	    public List<Transaction> getAllTransactionsByCustomerId(@PathVariable Long customerId) {
	        return transactionRepository.getAllTransactionsByCustomerId(customerId);
	    }
	  
	  
	  
	  @GetMapping("/transactions")
      public List<Transaction> getAllTransactions() {
          return transactionService.getAllTransactions();
      }
}

