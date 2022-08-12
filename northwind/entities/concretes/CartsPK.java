package com.etiya.northwind.entities.concretes;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartsPK implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private String customerId;
	private int productId;
}
