package com.sprint.models;


import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="transaction")
public class Transaction {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="transaction_id")
private long transactionId;

@Column(name="date")
private LocalDate date;

//@Column(name="end_date")
//private LocalDate date1;

@Column(name="cost")
private double cost;

@Column(name = "payment_mode")
@Enumerated(EnumType.STRING)
private PaymentMode paymentMode;

@JsonIgnore
@ManyToOne
@JoinColumn(name = "admin_id")
private Admin admin;

@JsonIgnore
@ManyToOne
@JoinColumn(name = "customer_id", nullable = true)
private Customer customer;

@ManyToOne
 @JoinColumn(name = "booking_id")
 private Booking booking;

}