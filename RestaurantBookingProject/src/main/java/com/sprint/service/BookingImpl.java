package com.sprint.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.exceptions.BookingNotFoundException;
import com.sprint.dto.BookingDTO;
import com.sprint.exceptions.BookingAlreadyExistsException;
import com.sprint.models.Admin;
import com.sprint.models.Booking;
import com.sprint.models.Customer;
import com.sprint.repository.BookingRepository;

@Service
public class BookingImpl implements BookingService
{
	private BookingRepository bookingRepository;
	@Autowired
	public BookingImpl(BookingRepository bookingRepository){
		this.bookingRepository = bookingRepository;
	}
	
	
	
//	
	@Override
	public BookingDTO bookTable(long custId, LocalTime time, BookingDTO booking) throws BookingAlreadyExistsException {
	    List<Booking> existingBookings = bookingRepository.findByDateAndTimeAndTableNumber(booking.getDate(), time, booking.getTableNumber());
	    if (!existingBookings.isEmpty()) {
	        throw new BookingAlreadyExistsException("This table is already booked for the selected date and time");
	    } else {
	        Admin admin = new Admin();
	        admin.setAdminId(1);
	        Customer customer = new Customer();
	        customer.setCustomerId(custId);
	        Booking newBooking = new Booking();
	        newBooking.setDate(booking.getDate());
	        newBooking.setTime(time);
	        newBooking.setTableNumber(booking.getTableNumber());
	        newBooking.setAdmin(admin);
	        newBooking.setCustomer(customer);
	        newBooking.setNumberOfGuests(booking.getNumberOfGuests());
	        Booking savedBooking = bookingRepository.save(newBooking);
	        booking.setId(savedBooking.getId());
	        return booking;
	    }
	}

	@Override
	public List<Booking> findBookingByDate(LocalDate date)throws BookingNotFoundException{
		List<Booking>  existBooking = bookingRepository.findByDate(date);
		if(existBooking.isEmpty()) {
			throw new BookingNotFoundException("Booking records are not found for given date ");
		}
		else {
			return bookingRepository.findByDate(date);
		}
}
	
	@Override
	public Booking updateBooking(long bookingId, LocalDate newDate, LocalTime newTime) throws BookingNotFoundException {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + bookingId));
        booking.setDate(newDate);
        booking.setTime(newTime);
        return bookingRepository.save(booking);
    }


	@Override
	public void cancelBooking(Long bookingId) throws BookingNotFoundException {
		Booking booking = bookingRepository.findById(bookingId)
			      .orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + bookingId));
			    bookingRepository.delete(booking);
		
	}


	public List<String> displayTableBookings(int tableNumber, LocalDate date) throws BookingNotFoundException {
	    List<Booking> bookings = bookingRepository.findByTableNumberAndDate(tableNumber, date);
	    if (bookings.isEmpty()) {
	    	
	        throw new BookingNotFoundException("No bookings found for table number " + tableNumber + " and date " + date);
	    }
	    List<String> bookingDetails = new ArrayList<>();
	    for (Booking booking : bookings) {
	        String bookingDetail = "Customer ID: " + booking.getCustomer().getCustomerId()
	                             + " Date: " + booking.getDate()
	                             + " Time: " + booking.getTime()
	                             + " Number of guests: " + booking.getNumberOfGuests()
	                             + " Table number: " + booking.getTableNumber();
	        bookingDetails.add(bookingDetail);
	    }
	    return bookingDetails;
	}

	}


