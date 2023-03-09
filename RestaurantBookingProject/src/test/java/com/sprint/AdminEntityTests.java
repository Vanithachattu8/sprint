package com.sprint;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sprint.models.Admin;
import com.sprint.models.Customer;
import com.sprint.models.Restaurant;
import com.sprint.models.Transaction;


class AdminEntityTests {
    
    private Admin admin;
    
    @BeforeEach
     void setUp() {
        admin = new Admin();
        admin.setAdminId(1L);
        admin.setAdminName("John");
        admin.setEmailId("john@example.com");
        admin.setPassword("password");
    }
    
    @Test
    @DisplayName("Test Admin getters")
    void testGetters() {
        assertEquals(1L, admin.getAdminId());
        assertEquals("John", admin.getAdminName());
        assertEquals("john@example.com", admin.getEmailId());
        assertEquals("password", admin.getPassword());
    }
    
    @Test
    @DisplayName("Test Admin setters")
    void testSetters() {
        admin.setAdminId(2L);
        admin.setAdminName("Jane");
        admin.setEmailId("jane@example.com");
        admin.setPassword("new_password");
        
        assertEquals(2L, admin.getAdminId());
        assertEquals("Jane", admin.getAdminName());
        assertEquals("jane@example.com", admin.getEmailId());
        assertEquals("new_password", admin.getPassword());
    }
    
    @Test
    @DisplayName("Test Admin transaction list")
    void testTransactionList() {
        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction1 = new Transaction();
        transaction1.setTransactionId(1L);
        Transaction transaction2 = new Transaction();
        transaction2.setTransactionId(2L);
        transactions.add(transaction1);
        transactions.add(transaction2);
        
        admin.setTransaction(transactions);
        
        assertEquals(2, admin.getTransaction().size());
        assertEquals(transaction1, admin.getTransaction().get(0));
        assertEquals(transaction2, admin.getTransaction().get(1));
    }
    
    @Test
    @DisplayName("Test Admin customer list")
    void testCustomerList() {
        List<Customer> customers = new ArrayList<>();
        Customer customer1 = new Customer();
        customer1.setCustomerId(1L);
        Customer customer2 = new Customer();
        customer2.setCustomerId(2L);
        customers.add(customer1);
        customers.add(customer2);
        
        admin.setCustomer(customers);
        
        assertEquals(2, admin.getCustomer().size());
        assertEquals(customer1, admin.getCustomer().get(0));
        assertEquals(customer2, admin.getCustomer().get(1));
    }
    
    @Test
    @DisplayName("Test Admin restaurant set")
    void testRestaurantSet() {
        Set<Restaurant> restaurants = new HashSet<>();
        Restaurant restaurant1 = new Restaurant();
        restaurant1.setRestaurantId(1L);
        Restaurant restaurant2 = new Restaurant();
        restaurant2.setRestaurantId(2L);
        restaurants.add(restaurant1);
        restaurants.add(restaurant2);
        
        admin.setRestaurant(restaurants);
        
        assertEquals(2, admin.getRestaurant().size());
        assertTrue(admin.getRestaurant().contains(restaurant1));
        assertTrue(admin.getRestaurant().contains(restaurant2));
    }
}