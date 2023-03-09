package com.sprint.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.exceptions.TableNotFoundException;
import com.sprint.models.Admin;
import com.sprint.models.Restaurant;
import com.sprint.repository.RestaurantRepository;

@Service
public class RestaurantImpl implements RestaurantService{

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Override
	public Restaurant createRestaurant(Restaurant restaurant) {
		Admin admin=new Admin();
		admin.setAdminId(1);
		restaurant.setAdmin(admin);
		return restaurantRepository.save(restaurant);
	}

	@Override
    public List<Restaurant> getTablesBySeating(int seatingCapacity)throws TableNotFoundException{
        List<Restaurant> restaurants = restaurantRepository.findBySeatingCapacity(seatingCapacity);
            if (restaurants.isEmpty()) {
                throw new TableNotFoundException("No Tables found with seating capacity " + seatingCapacity);
                }
            return restaurants;
            }
	
	@Override
	public List<Restaurant> getAllAvailableTables() {
		return restaurantRepository.findAll();
		
	}
	@Override
    public List<Restaurant> getTablesAvailableOnDateTime(LocalDate date,LocalTime time,LocalTime endTime) throws TableNotFoundException{
        List<Restaurant> restaurants = restaurantRepository.findTablesOnDateTime(date,time, endTime);
        if (restaurants.isEmpty()) {
            throw new TableNotFoundException("No Tables found for given date and time" );
            }
        return restaurants;
        }


}
