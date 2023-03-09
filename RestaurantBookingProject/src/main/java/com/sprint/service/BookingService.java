package com.sprint.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import com.sprint.exceptions.BookingNotFoundException;
import com.sprint.dto.BookingDTO;
import com.sprint.exceptions.BookingAlreadyExistsException;
import com.sprint.models.Booking;

public interface BookingService {
	List<Booking> findBookingByDate(LocalDate date)throws BookingNotFoundException;
	Booking updateBooking(long bookingId,LocalDate newDate,LocalTime newTime) throws BookingNotFoundException;
	public BookingDTO bookTable(long custId,LocalTime time,BookingDTO bookingDTO) throws BookingAlreadyExistsException;
	public void cancelBooking(Long bookingId) throws BookingNotFoundException;
}