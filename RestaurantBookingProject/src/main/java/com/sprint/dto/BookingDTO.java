package com.sprint.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;
		
		@Column(name = "date")
		private LocalDate date;
		
		@Column(name="table_number")
		private int tableNumber;
		
		@Column(name = "number_of_guests")
		private int numberOfGuests;
		
		@JsonIgnore
		@Column(name = "time")
		private LocalTime time;
}
