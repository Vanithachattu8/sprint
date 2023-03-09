package com.sprint;

 

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.time.LocalDate;
import org.junit.Before;
    import org.junit.Test;
    import com.sprint.models.Admin;
    import com.sprint.models.Booking;
    import com.sprint.models.Customer;
    import com.sprint.models.Transaction;
    public class TransactionEntityTests {
        private Transaction transaction;
        @Before
        public void setUp() {
            transaction = new Transaction();
            }
        @Test
        public void testGetSetTransactionId() {
            long id = 1L;
            transaction.setTransactionId(id);
            assertEquals(id, transaction.getTransactionId());
            }
        @Test
        public void testGetSetDate() {
            LocalDate date = LocalDate.now();
            transaction.setDate(date);
            assertEquals(date, transaction.getDate());
            }
        @Test
        public void testGetSetDate1() {
            LocalDate date1 = LocalDate.now();
            transaction.setDate(date1);
            assertEquals(date1, transaction.getDate());
            }
        
        @Test
        public void testGetSetCost() {
            double cost = 100.0;
            transaction.setCost(cost);
            assertEquals(cost, transaction.getCost(), 0.0);
            }
        @Test
        public void testGetSetAdmin() {
            Admin admin = new Admin();
            transaction.setAdmin(admin);
            assertNotNull(transaction.getAdmin());
            }
        @Test
        public void testGetSetCustomer() {
            Customer customer = new Customer();
            transaction.setCustomer(customer);
            assertNotNull(transaction.getCustomer());
            }
        @Test
        public void testGetSetBooking() {
            Booking booking = new Booking();
            transaction.setBooking(booking);
            assertNotNull(transaction.getBooking());
            }
    }