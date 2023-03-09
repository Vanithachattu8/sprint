package com.sprint.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "restaurant")
public class Restaurant {
	
	@Column(name="restaurant_id")
	private long restaurantId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "table_number")
	private int tableNumber;
	
	@Column(name = "seating")
	private int seatingCapacity;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "admin_id")
	private Admin admin;
}
