package com.sprint.service;
import java.time.LocalDate;

import java.time.LocalTime;
import java.util.List;

import com.sprint.exceptions.TableNotFoundException;
import com.sprint.models.Restaurant;
public interface RestaurantService {
	 public List<Restaurant> getTablesBySeating(int seatingCapacity) throws TableNotFoundException;
	    public List<Restaurant> getAllAvailableTables();
	    public Restaurant createRestaurant(Restaurant restaurant);
	    List<Restaurant> getTablesAvailableOnDateTime(LocalDate date, LocalTime startTime, LocalTime endTime) throws TableNotFoundException;
}


