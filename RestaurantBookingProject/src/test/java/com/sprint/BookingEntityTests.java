package com.sprint;

 

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

 

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

 

import org.junit.Before;
import org.junit.Test;

 

import com.sprint.models.Admin;
import com.sprint.models.Booking;
import com.sprint.models.Transaction;

 

public class BookingEntityTests {

 

    private Booking booking;

 

    @Before
    public void setUp() {
        booking = new Booking();
    }

 

    @Test
    public void testId() {
        booking.setId(1);
        assertEquals(1, booking.getId());
    }

 

    @Test
    public void testDate() {
        LocalDate date = LocalDate.of(2023, 3, 8);
        booking.setDate(date);
        assertEquals(date, booking.getDate());
    }

 

    @Test
    public void testTime() {
        LocalTime time = LocalTime.of(12, 0);
        booking.setTime(time);
        assertEquals(time, booking.getTime());
    }

 

    @Test
    public void testNumberOfGuests() {
        booking.setNumberOfGuests(4);
        assertEquals(4, booking.getNumberOfGuests());
    }

 

    @Test
    public void testTableNumber() {
        booking.setTableNumber(2);
        assertEquals(2, booking.getTableNumber());
    }

 

    @Test
    public void testTransaction() {
        Transaction transaction = new Transaction();
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        booking.setTransaction(transactions);
        assertEquals(transactions, booking.getTransaction());
    }

 

    @Test
    public void testAdmin() {
        Admin admin = new Admin();
        booking.setAdmin(admin);
        assertEquals(admin, booking.getAdmin());
    }

 

}