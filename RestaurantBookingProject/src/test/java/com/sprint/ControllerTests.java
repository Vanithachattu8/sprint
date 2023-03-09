package com.sprint;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import java.util.List;


import org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sprint.controllers.AdminController;
import com.sprint.controllers.CustomerController;
import com.sprint.dto.BookingDTO;
import com.sprint.exceptions.BookingAlreadyExistsException;
import com.sprint.exceptions.BookingNotFoundException;
import com.sprint.models.Booking;

import com.sprint.repository.BookingRepository;
import com.sprint.repository.CustomerRepository;
import com.sprint.service.BookingImpl;

@RunWith(MockitoJUnitRunner.class)
	public class ControllerTests {
	
	    @Mock
	    private BookingImpl bookingService;
	    @Mock
	    private CustomerRepository customerRepository;
	    @Mock
	    private BookingRepository bookingRepository;
	    @Mock
	    private org.springframework.ui.Model model;
	    @InjectMocks
	    private CustomerController customerController;
	    
	    @InjectMocks
	    private AdminController adminController;
	    
	    @Test
	    public void testGetBookingsForTable() throws BookingNotFoundException {
	        // Set up mock behavior for service method call
	        List<String> bookingDetails = new ArrayList<>();
	        bookingDetails.add("Booking details 1");
	        bookingDetails.add("Booking details 2");
	        when(bookingService.displayTableBookings(1, LocalDate.of(2023, 03, 9))).thenReturn(bookingDetails);
	        
	        // Call the controller method being tested
	        ResponseEntity<List<String>> response = adminController.getBookingsForTable(1, LocalDate.of(2023, 03, 9));
	        
	        // Verify the response
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(bookingDetails, response.getBody());
	    }
	    @Test
	    public void testBookTable() throws BookingAlreadyExistsException {
	        // Set up mock behavior for service method calls
	        BookingDTO booking = new BookingDTO();
	        booking.setDate(LocalDate.now());
	        booking.setNumberOfGuests(2);
	        booking.setTableNumber(1);
	        BookingDTO mockBooking = new BookingDTO();
	        mockBooking.setId(1L);
	        mockBooking.setDate(booking.getDate());
	        mockBooking.setNumberOfGuests(booking.getNumberOfGuests());
	        mockBooking.setTableNumber(booking.getTableNumber());
	        when(bookingService.bookTable(1L, LocalTime.parse("12:00"), booking)).thenReturn(mockBooking);

	        // Call the method being tested
	        ResponseEntity<String> response = customerController.bookTable(1L, "12:00", booking);

	        // Verify the result
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        String expectedMessage = "Table 1 has been booked for " + booking.getDate() + " at 12:00 for 2 guests by customer with ID 1 and Booking ID: 1";
	        assertEquals(expectedMessage, response.getBody());
	    }
	    
	     
	        
	     
	       
	     
	        @Test
	        public void testUpdateBooking() throws BookingNotFoundException {
	            // Prepare test data
	            Long bookingId = 1L;
	            LocalDate newDate = LocalDate.of(2022, 1, 1);
	            String newTime = "10:00";
	            Booking updatedBooking = new Booking();
	            updatedBooking.setId(bookingId);
	            updatedBooking.setDate(newDate);
	            updatedBooking.setTime(LocalTime.parse(newTime));
	     
	            // Mock the bookingService
	            Mockito.when(bookingService.updateBooking(bookingId, newDate, LocalTime.parse(newTime))).thenReturn(updatedBooking);
	     
	            // Call the controller method
	            ResponseEntity<String> response = customerController.updateBooking(bookingId, newDate, newTime);
	     
	            // Verify the response
	            assertEquals(HttpStatus.OK, response.getStatusCode());
	            assertEquals("Booking ID " + updatedBooking.getId() + " has been updated with new date: " + updatedBooking.getDate() + " and time: " + updatedBooking.getTime(), response.getBody());
	     
	            // Verify the bookingService call
	            Mockito.verify(bookingService, Mockito.times(1)).updateBooking(bookingId, newDate, LocalTime.parse(newTime));
	        }
	     
	        @Test(expected = BookingNotFoundException.class)
	        public void testUpdateBookingWithInvalidBookingId() throws BookingNotFoundException {
	            // Prepare test data
	            Long bookingId = 2L;
	            LocalDate newDate = LocalDate.of(2022, 1, 1);
	            String newTime = "10:00";
	     
	            // Mock the bookingService to throw BookingNotFoundException
	            Mockito.when(bookingService.updateBooking(bookingId, newDate, LocalTime.parse(newTime))).thenThrow(BookingNotFoundException.class);
	     
	            // Call the controller method
	            customerController.updateBooking(bookingId, newDate, newTime);
	     
	            // Verify the bookingService call
	            Mockito.verify(bookingService, Mockito.times(1)).updateBooking(bookingId, newDate, LocalTime.parse(newTime));
	        }
	        @Test
	        public void testCancelBooking() throws BookingNotFoundException {
	          Long bookingId = 1L;
	          
	          // Mock the behavior of the bookingService
	          doNothing().when(bookingService).cancelBooking(bookingId);
	          
	          // Call the controller method
	          ResponseEntity<String> response = customerController.cancelBooking(bookingId);
	          
	          // Verify the behavior
	          verify(bookingService, times(1)).cancelBooking(bookingId);
	          assertEquals("Booking with ID 1 has been cancelled.", response.getBody());
	          assertEquals(HttpStatus.OK, response.getStatusCode());
	        }
	        
	        @Test(expected = BookingNotFoundException.class)
	        public void testCancelBookingNotFoundException() throws BookingNotFoundException {
	          Long bookingId = 1L;
	          
	          // Mock the behavior of the bookingService to throw an exception
	          doThrow(BookingNotFoundException.class).when(bookingService).cancelBooking(bookingId);
	          
	          // Call the controller method
	          customerController.cancelBooking(bookingId);
	          
	          // The method should have thrown a BookingNotFoundException
	        }
	        @Test
	        public void testGetBookings() throws BookingNotFoundException {
	            // Arrange
	            LocalDate date = LocalDate.of(2023, 3, 8);
	            List<Booking> expectedBookings = new ArrayList<>();
	            expectedBookings.add(new Booking());
	            expectedBookings.add(new Booking());
	            Mockito.when(bookingService.findBookingByDate(date)).thenReturn(expectedBookings);

	            // Act
	            List<Booking> actualBookings = adminController.getBookings(date, model);

	            // Assert
	            Assert.assertEquals(expectedBookings, actualBookings);
	            Mockito.verify(bookingService).findBookingByDate(date);
	            Mockito.verify(model).addAttribute("bookings", expectedBookings);
	        }
	    }
	      
	    



	


