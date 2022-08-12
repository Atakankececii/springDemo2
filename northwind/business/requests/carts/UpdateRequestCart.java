package com.etiya.northwind.business.requests.carts;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRequestCart {

	private String customerId;
	private int productId;
	private int quantity;
	private double unitPrice;
}
