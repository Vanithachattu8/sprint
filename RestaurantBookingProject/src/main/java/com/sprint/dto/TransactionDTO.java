package com.sprint.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sprint.models.PaymentMode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="transaction")
public class TransactionDTO {

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="transaction_id")
		private long transactionId;

		@Column(name="date")
		private LocalDate date;

		@Column(name="cost")
		private double cost;
		
		@Column(name = "payment_mode")
		@Enumerated(EnumType.STRING)
		private PaymentMode paymentMode;
		

}
