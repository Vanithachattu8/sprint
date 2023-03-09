package com.sprint.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.sprint.models.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Long>{ 
	@Query("SELECT SUM(r.cost) FROM Transaction r JOIN r.booking b JOIN b.customer c WHERE c.customerId = :customerId AND r.date >= :startDate AND r.date < :endDate")
	Double findTotalAmountSpentByCustomerIdBetweenDates(@Param("customerId") Long customerId,
	                                                     @Param("endDate") LocalDate endDate,
	                                                     @Param("startDate") LocalDate startDate);

	@Query("SELECT t FROM Transaction t WHERE t.booking=: booking")
	Transaction findTransactionId(@Param("booking")long bookingId);
	
	@Query("SELECT SUM(r.cost) FROM Transaction r JOIN r.booking b JOIN b.customer c WHERE c.customerId = :customerId")
	Double findTotalCostSpent(@Param("customerId") Long customerId);
	
	List<Transaction> findByDate(LocalDate date);
	
	
	 @Query("SELECT r FROM Transaction r JOIN r.booking b JOIN b.customer c WHERE c.customerId = :customerId")
	    List<Transaction> getAllTransactionsByCustomerId(@Param("customerId") Long customerId);
}
