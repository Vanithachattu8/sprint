package com.sprint;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.sprint.exceptions.TableNotFoundException;
import com.sprint.models.Restaurant;
import com.sprint.repository.RestaurantRepository;
import com.sprint.service.RestaurantImpl;
@SpringBootTest
class RestaurantImplTests {
    @Mock
    private RestaurantRepository restaurantRepository;
    @InjectMocks
    private RestaurantImpl restaurantImpl;
    @Test
    void testCreateRestaurant() {
        Restaurant restaurant = new Restaurant();
        restaurant.setSeatingCapacity(4);
        when(restaurantRepository.save(restaurant)).thenReturn(restaurant);
        Restaurant result = restaurantImpl.createRestaurant(restaurant);
        verify(restaurantRepository, times(1)).save(restaurant);
        assertSame(restaurant, result);
        }
        @Test
        void testGetTablesBySeating() throws TableNotFoundException {
            List<Restaurant> tables = new ArrayList<>();
            tables.add(new Restaurant());
            tables.add(new Restaurant());
            when(restaurantRepository.findBySeatingCapacity(4)).thenReturn(tables);
            List<Restaurant> result = restaurantImpl.getTablesBySeating(4);
            verify(restaurantRepository, times(1)).findBySeatingCapacity(4);
            assertSame(tables, result);
            }
        @Test
        void testGetAllAvailableTables() {
            List<Restaurant> tables = new ArrayList<>();
            tables.add(new Restaurant());
            tables.add(new Restaurant());
            when(restaurantRepository.findAll()).thenReturn(tables);
            List<Restaurant> result = restaurantImpl.getAllAvailableTables();
            verify(restaurantRepository, times(1)).findAll();
            assertSame(tables, result);
            }
        @Test
       void testGetTablesAvailableOnDateTime() throws TableNotFoundException {
        LocalDate date = LocalDate.now();
        LocalTime startTime = LocalTime.of(10, 0);
        LocalTime endTime = LocalTime.of(12, 0);
        List<Restaurant> tables = new ArrayList<>();
        tables.add(new Restaurant());
        tables.add(new Restaurant());
        when(restaurantRepository.findTablesOnDateTime(date, startTime, endTime)).thenReturn(tables);
        List<Restaurant> result = restaurantImpl.getTablesAvailableOnDateTime(date, startTime, endTime);
        verify(restaurantRepository, times(1)).findTablesOnDateTime(date, startTime, endTime);
        assertSame(tables, result);
        }
}