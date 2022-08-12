package com.etiya.northwind.business.responses.carts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartListResponse {


	private String customerId;
	private int quantity;
	private double unitPrice;
	private int productId;
	private String productName;
	

	
}
