package com.sprint;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.sprint.dto.BookingDTO;
import com.sprint.exceptions.BookingAlreadyExistsException;
import com.sprint.exceptions.BookingNotFoundException;
import com.sprint.models.Admin;
import com.sprint.models.Booking;
import com.sprint.models.Customer;
import com.sprint.repository.BookingRepository;
import com.sprint.service.BookingImpl;

@RunWith(MockitoJUnitRunner.class)
public class BookingImplTests {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingImpl bookingService;

    private LocalDate bookingDate;
    private LocalTime bookingTime;
    private BookingDTO bookingDTO;
    private Customer customer;
    private Admin admin;
    private Booking booking;

    @Before
    public void setUp() {
        bookingDate = LocalDate.of(2023, 3, 10);
        bookingTime = LocalTime.of(18, 30);
        bookingDTO = new BookingDTO();
        bookingDTO.setDate(bookingDate);
        bookingDTO.setTime(bookingTime);
        bookingDTO.setTableNumber(1);
        bookingDTO.setNumberOfGuests(4);
        customer = new Customer();
        customer.setCustomerId(1L);
        admin = new Admin();
        admin.setAdminId(1L);
        booking = new Booking();
        booking.setId(1L);
        booking.setDate(bookingDate);
        booking.setTime(bookingTime);
        booking.setTableNumber(1);
        booking.setNumberOfGuests(4);
        booking.setCustomer(customer);
        booking.setAdmin(admin);
    }

    @Test
    public void testBookTable() throws BookingAlreadyExistsException {
        // mock the repository to return an empty list of existing bookings
        when(bookingRepository.findByDateAndTimeAndTableNumber(bookingDate, bookingTime, 1))
                .thenReturn(new ArrayList<Booking>());

        // mock the repository to return the saved booking
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        // call the service method
        BookingDTO result = bookingService.bookTable(1L, bookingTime, bookingDTO);

        // verify that the booking was saved and its id was set in the DTO
        verify(bookingRepository).save(any(Booking.class));
        assertEquals(booking.getId(), result.getId());
    }

    @Test(expected = BookingAlreadyExistsException.class)
    public void testBookTableThrowsException() throws BookingAlreadyExistsException {
        // mock the repository to return a non-empty list of existing bookings
        List<Booking> existingBookings = new ArrayList<>();
        existingBookings.add(booking);
        when(bookingRepository.findByDateAndTimeAndTableNumber(bookingDate, bookingTime, 1))
                .thenReturn(existingBookings);

        // call the service method and expect an exception to be thrown
        bookingService.bookTable(1L, bookingTime, bookingDTO);
    }
    @Test
    public void testFindBookingByDate() throws BookingNotFoundException {
        LocalDate date = LocalDate.of(2022, 4, 1);
        List<Booking> bookings = new ArrayList<>();
        Booking booking1 = new Booking();
        booking1.setDate(date);
        bookings.add(booking1);

        when(bookingRepository.findByDate(any(LocalDate.class))).thenReturn(bookings);

        List<Booking> result = bookingService.findBookingByDate(date);

        assertEquals(bookings, result);
    }

//    @Test
//    public void testFindBookingByDateNotFound() throws BookingNotFoundException {
//        LocalDate date = LocalDate.of(2022, 4, 1);
//        List<Booking> bookings = new ArrayList<>();
//
//        when(bookingRepository.findByDate(any(LocalDate.class))).thenReturn(bookings);
//
//        assertThrows(BookingNotFoundException.class, () -> bookingService.findBookingByDate(date));
//    }






    @Test(expected = BookingNotFoundException.class)
    public void testFindBookingByDateThrowsException() throws BookingNotFoundException {
        // mock the repository to return an empty list of bookings for the given date
        when(bookingRepository.findByDate(bookingDate)).thenReturn(new ArrayList<Booking>());

        // call the service method and expect an exception to be thrown
        bookingService.findBookingByDate(bookingDate);
    }
    @Test
    public void testUpdateBooking() throws BookingNotFoundException {
        // Arrange
        long bookingId = 123;
        LocalDate newDate = LocalDate.of(2023, 3, 9);
        LocalTime newTime = LocalTime.of(13, 30);
        Booking existingBooking = new Booking();
        existingBooking.setId(bookingId);
        existingBooking.setDate(LocalDate.of(2023, 3, 8));
        existingBooking.setTime(LocalTime.of(10, 0));
        when(bookingRepository.findById(bookingId)).thenReturn(java.util.Optional.of(existingBooking));
        when(bookingRepository.save(existingBooking)).thenReturn(existingBooking);

        // Act
        Booking updatedBooking = bookingService.updateBooking(bookingId, newDate, newTime);

        // Assert
        assertEquals(existingBooking, updatedBooking);
        assertEquals(newDate, updatedBooking.getDate());
        assertEquals(newTime, updatedBooking.getTime());
        verify(bookingRepository, times(1)).findById(bookingId);
        verify(bookingRepository, times(1)).save(existingBooking);
    }

    @Test(expected = BookingNotFoundException.class)
    public void testUpdateBookingWithNonExistingBooking() throws BookingNotFoundException {
        // Arrange
        long bookingId = 123;
        LocalDate newDate = LocalDate.of(2023, 3, 9);
        LocalTime newTime = LocalTime.of(13, 30);
        when(bookingRepository.findById(bookingId)).thenReturn(java.util.Optional.empty());

        // Act
        bookingService.updateBooking(bookingId, newDate, newTime);

        // Assert
        // Expects BookingNotFoundException to be thrown
    }
    @Test
    public void testCancelBooking() throws BookingNotFoundException {
        // Create a booking object
        Booking booking = new Booking();
        booking.setId(1L);
        // Create a mock bookingRepository object
        BookingRepository bookingRepository = Mockito.mock(BookingRepository.class);
        // Set up the mock bookingRepository to return the booking object when findById() method is called
        Mockito.when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));
        // Create an instance of the BookingServiceImpl class and inject the mock bookingRepository object
        BookingImpl bookingService = new BookingImpl(bookingRepository);
        // Call the cancelBooking() method with the booking ID
        bookingService.cancelBooking(1L);
        // Verify that the delete() method of the mock bookingRepository is called with the booking object as an argument
        Mockito.verify(bookingRepository, Mockito.times(1)).delete(booking);
    }
    @Test(expected = BookingNotFoundException.class)
    public void testCancelBookingWithException() throws BookingNotFoundException {
        // Create a mock bookingRepository object
        BookingRepository bookingRepository = Mockito.mock(BookingRepository.class);
        // Set up the mock bookingRepository to return an empty optional when findById() method is called
        Mockito.when(bookingRepository.findById(1L)).thenReturn(Optional.empty());
        // Create an instance of the BookingServiceImpl class and inject the mock bookingRepository object
        BookingImpl bookingService = new BookingImpl(bookingRepository);
        // Call the cancelBooking() method with the booking ID
        bookingService.cancelBooking(1L);
        // Verify that the delete() method of the mock bookingRepository is never called
        Mockito.verify(bookingRepository, Mockito.never()).delete(Mockito.any(Booking.class));
    }

//    @Test
//    public void testDisplayTableBookings() throws BookingNotFoundException {
//        // Set up mock behavior for repository method call
//        List<Booking> bookings = new ArrayList<>();
//        Booking booking = new Booking();
//        booking.setCustomer(new Customer());
//        booking.setDate(LocalDate.now());
//        booking.setTime(LocalTime.now());
//        booking.setNumberOfGuests(2);
//        booking.setTableNumber(1);
//        bookings.add(booking);
//        when(bookingRepository.findByTableNumberAndDate(1, LocalDate.now())).thenReturn(bookings);
//
//        // Call the method being tested
//        List<String> bookingDetails = bookingService.displayTableBookings(1, LocalDate.now());
//
//        // Verify the result
//        assertEquals(1, bookingDetails.size());
//        assertEquals("Customer ID: " + booking.getCustomer().getCustomerId() +
//                     " Date: " + booking.getDate() +
//                     " Time: " + booking.getTime() +
//                     " Number of guests: 2 Table number: 1", bookingDetails.get(0));
//    }
    @Test
    public void testDisplayTableBookings() throws BookingNotFoundException {
        // Set up mock behavior for repository method call
        List<Booking> bookings = new ArrayList<>();
        Booking booking = new Booking();
        booking.setCustomer(new Customer());
        booking.setDate(LocalDate.now());
        booking.setTime(LocalTime.now());
        booking.setNumberOfGuests(2);
        booking.setTableNumber(1);
        bookings.add(booking);
        when(bookingRepository.findByTableNumberAndDate(1, LocalDate.now())).thenReturn(bookings); // Define the behavior of the mock repository

        // Call the method being tested
        List<String> bookingDetails = bookingService.displayTableBookings(1, LocalDate.now());

        // Verify the result
        assertEquals(1, bookingDetails.size());
        assertEquals("Customer ID: " + booking.getCustomer().getCustomerId() +
                     " Date: " + booking.getDate() +
                     " Time: " + booking.getTime() +
                     " Number of guests: 2 Table number: 1", bookingDetails.get(0));
        verify(bookingRepository, times(1)).findByTableNumberAndDate(1, LocalDate.now()); // Verify that the mock repository method was called once with the correct arguments
    }

    @Test
    public void testDisplayTableBookings_NoBookingsFound() {
        // Set up mock behavior for repository method call
        when(bookingRepository.findByTableNumberAndDate(1, LocalDate.now())).thenReturn(new ArrayList<>());

        // Call the method being tested and verify the exception
        assertThrows(BookingNotFoundException.class, () -> bookingService.displayTableBookings(1, LocalDate.now()));
    }

}



