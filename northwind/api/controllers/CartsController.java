package com.etiya.northwind.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.northwind.business.abstracts.CartService;
import com.etiya.northwind.business.requests.carts.CreateRequestAddToOrder;
import com.etiya.northwind.business.requests.carts.CreateRequestCart;
import com.etiya.northwind.business.requests.carts.DeleteRequestCart;
import com.etiya.northwind.business.requests.carts.UpdateRequestCart;
import com.etiya.northwind.business.responses.carts.CartListResponse;
import com.etiya.northwind.business.responses.carts.GetCartResponse;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;

@RestController
@RequestMapping("/api/carts")
public class CartsController {

	private CartService cartService;

	@Autowired
	public CartsController(CartService cartService) {
		
		this.cartService = cartService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<CartListResponse>> getAll(){
		return this.cartService.getAll();
	}
	

	@PostMapping("/add")
	public Result add(@RequestBody CreateRequestCart createRequestCart) {
		return this.cartService.add(createRequestCart);
	}
	
	@GetMapping("/getById")
	public DataResult<GetCartResponse> getById(@RequestParam String customerId ,@RequestParam int productId){
		return this.cartService.getById(customerId,productId);
	
		
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody UpdateRequestCart updateRequestCart) {
		return this.cartService.update(updateRequestCart);
	}
	
	@PostMapping("/delete")
	public Result delete(@RequestBody DeleteRequestCart deleteRequestCart) {
		return this.cartService.delete(deleteRequestCart);
	}
	
	@PostMapping("/addToOrder")
	public Result addToOrder(@RequestBody CreateRequestAddToOrder createRequestAddToOrder) {
		return this.cartService.addToOrder(createRequestAddToOrder);
	}
	
	
	
	
}
