package com.sprint.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sprint.models.Booking;


@Repository
public interface BookingRepository extends JpaRepository<Booking,Long>{
	List<Booking> findByDate(@Param("startDate")LocalDate date);
	
//	@Query("SELECT b FROM Booking b WHERE b.date = :date AND b.time = :time AND b.numberOfGuests = :numberOfGuests AND b.tableNumber = :tableNumber")
//	List<Booking> findByDateTimeAndNumberOfGuestsAndTableNumber(@Param("date") LocalDate date, @Param("time") LocalTime time, @Param("numberOfGuests") int numberOfGuests, @Param("tableNumber") int tableNumber);

	List<Booking> findByTableNumberAndDate(int tableNumber, LocalDate date);
	
	List<Booking> findByDateAndTimeAndTableNumber(LocalDate date, LocalTime time, int tableNumber);
}
