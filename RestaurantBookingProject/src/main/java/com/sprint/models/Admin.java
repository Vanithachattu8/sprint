package com.sprint.models;

import java.util.List;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="admin")
public class Admin {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="admin_id")
	private long adminId;
	
	@Column(name="admin_name")
	private String adminName;
	
	@Column(name="email_Id")
	public String emailId;
	
	@Column(name="password")
	public String password;
	
	@JsonIgnore
	@OneToMany(mappedBy = "admin", fetch = FetchType.LAZY)
	//@JoinColumn(name = "transaction_id")
	private List<Transaction> transaction;
	
	@JsonIgnore
	@OneToMany(mappedBy = "admin", fetch = FetchType.LAZY)
	//@JoinColumn(name = "customer_id")
	private List<Customer> customer;
	
	@JsonIgnore
	@OneToMany(mappedBy="admin")
	//@JoinColumn(name = "table_number")
	private Set<Restaurant> restaurant;
	
	@JsonIgnore
	@OneToMany(mappedBy = "admin", fetch = FetchType.LAZY)
	//@JoinColumn(name = "booking_id")
	private List<Booking> booking;
	
}
