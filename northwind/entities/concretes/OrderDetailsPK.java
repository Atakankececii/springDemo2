package com.etiya.northwind.entities.concretes;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsPK implements Serializable {
	private static final long serialVersionUID = 1L;

	private int orderId;
	private int productId;
}