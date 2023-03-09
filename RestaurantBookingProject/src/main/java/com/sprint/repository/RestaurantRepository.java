package com.sprint.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sprint.models.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long>{
	 List<Restaurant> findBySeatingCapacity(int seatingCapacity);
	 
	 @Query("SELECT t FROM Restaurant t WHERE t.tableNumber NOT IN (SELECT r.tableNumber FROM Booking b,Restaurant r WHERE b.date = :date AND b.time >= :time AND b.time<:endTime)ORDER BY t.seatingCapacity DESC")
	 List<Restaurant> findTablesOnDateTime(@Param("date")LocalDate date,@Param("time")LocalTime time,@Param("endTime")LocalTime endTime);
}
