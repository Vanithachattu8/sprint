package com.sprint;
 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
 
import java.util.ArrayList;
 
import org.junit.Before;
import org.junit.Test;
 
import com.sprint.models.Admin;
import com.sprint.models.Customer;
 
public class CustomerEntityTests {
 
    private Customer customer;
 
    @Before
    public void setUp() {
        customer = new Customer();
        customer.setCustomerId(1L);
        customer.setCustomerName("John Doe");
        customer.setPhoneNo(1234567890L);
        customer.setEmailId("johndoe@example.com");
        customer.setPassword("password123");
        customer.setTransactions(new ArrayList<>());
        customer.setBooking(new ArrayList<>());
        customer.setAdmin(new Admin());
    }
 
    @Test
    public void testCustomerId() {
        assertEquals(1L, customer.getCustomerId());
    }
 
    @Test
    public void testCustomerName() {
        assertEquals("John Doe", customer.getCustomerName());
    }
 
    @Test
    public void testPhoneNo() {
        assertEquals(1234567890L, customer.getPhoneNo());
    }
 
    @Test
    public void testEmailId() {
        assertEquals("johndoe@example.com", customer.getEmailId());
    }
 
    @Test
    public void testPassword() {
        assertEquals("password123", customer.getPassword());
    }
 
    @Test
    public void testTransactions() {
        assertNotNull(customer.getTransactions());
        assertEquals(new ArrayList<>(), customer.getTransactions());
    }
 
    @Test
    public void testBooking() {
        assertNotNull(customer.getBooking());
        assertEquals(new ArrayList<>(), customer.getBooking());
    }
 
    @Test
    public void testAdmin() {
        assertNotNull(customer.getAdmin());
    }
 
}