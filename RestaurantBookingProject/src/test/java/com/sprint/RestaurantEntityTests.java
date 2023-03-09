package com.sprint;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.sprint.models.Admin;
import com.sprint.models.Restaurant;

 

 class RestaurantEntityTests {

        @Test
        void testCreateRestaurant() {
            Restaurant restaurant = new Restaurant();
            Assertions.assertNotNull(restaurant);
        }
        
        @Test
        void testSetAndGetRestaurantId() {
            Restaurant restaurant = new Restaurant();
            restaurant.setRestaurantId(123456789L);
            Assertions.assertEquals(123456789L, restaurant.getRestaurantId());
        }


        @Test
        void testSetAndGetTableNumber() {
            Restaurant restaurant = new Restaurant();
            restaurant.setTableNumber(10);
            Assertions.assertEquals(10, restaurant.getTableNumber());
        }

        @Test
         void testSetAndGetSeatingCapacity() {
            Restaurant restaurant = new Restaurant();
            restaurant.setSeatingCapacity(4);
            Assertions.assertEquals(4, restaurant.getSeatingCapacity());
        }

        @Test
        void testSetAndGetAdmin() {
            Restaurant restaurant = new Restaurant();
            Admin admin = new Admin();
            restaurant.setAdmin(admin);
            Assertions.assertEquals(admin, restaurant.getAdmin());
        }

        @Test
        void testEquals() {
            Restaurant restaurant1 = new Restaurant();
            restaurant1.setTableNumber(10);
            Admin admin = new Admin();
            restaurant1.setAdmin(admin);
            Restaurant restaurant2 = new Restaurant();
            restaurant2.setTableNumber(10);
            restaurant2.setAdmin(admin);
            Assertions.assertEquals(restaurant1, restaurant2);
        }

        @Test
        void testGeneratedValueAnnotation() throws NoSuchFieldException {
            Class<?> restaurantClass = Restaurant.class;
            GeneratedValue generatedValue = restaurantClass.getDeclaredField("tableNumber").getAnnotation(GeneratedValue.class);
            Assertions.assertNotNull(generatedValue);
            Assertions.assertEquals(GenerationType.IDENTITY, generatedValue.strategy());
        }
    }