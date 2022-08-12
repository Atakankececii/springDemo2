package com.etiya.northwind.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "carts")
@IdClass(CartsPK.class)
public class Cart {

	@Id
	@Column(name = "customer_id")
	private String customerId;
	@Id
	@Column(name = "product_id")
	private int productId;
	
	@ManyToOne
	@JoinColumn(name = "product_id", insertable = false, updatable = false)
	private Product product;
	
	@ManyToOne
	@JoinColumn( name = "customer_id", insertable = false,updatable = false)
	private Customer customer;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "unit_price")
	private double unitPrice;
	
	
	
	
	
}
