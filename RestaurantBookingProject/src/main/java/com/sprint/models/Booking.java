package com.sprint.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="bookings")
public class Booking {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;

@Column(name = "date")
//@Temporal(TemporalType.DATE)
//@DateTimeFormat(style = "yyyy-MM-dd")
private LocalDate date;

@Column(name = "time")
private LocalTime time;

@Column(name = "number_of_guests")
private int numberOfGuests;

@Column(name="table_number")
private int tableNumber;

@JsonIgnore
@OneToMany(mappedBy = "booking", fetch = FetchType.LAZY)
private List<Transaction> transaction;

@JsonIgnore
@ManyToOne
 @JoinColumn(name = "admin_id")
 private Admin admin;

@ManyToOne(cascade=CascadeType.MERGE)
 @JoinColumn(name = "customer_id")
 private Customer customer;

@JsonIgnore
@JsonIgnoreProperties("booking")
@ManyToMany
//@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
@JoinTable(name="Restaurant_Booking",
        joinColumns = {@JoinColumn(name = "booking_id", nullable = false, updatable = false)},
        inverseJoinColumns = {@JoinColumn(name = "restaurant_id", nullable = false, updatable = false)}
)
private List<Restaurant> restaurantTables;
}