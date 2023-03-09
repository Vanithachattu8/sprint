package com.sprint.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="customer")
public class CustomerDTO {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="customer_id")
		private long customerId;

		@Column(name="customer_name")
		private String customerName;

		@Column(name="phone_no")
		private long phoneNo;
		
		@Column(name="email_id")
		private String emailId;

		@Column(name="password")
		private String password;



}
