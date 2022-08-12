package com.etiya.northwind.business.concretes;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.northwind.business.abstracts.CartService;
import com.etiya.northwind.business.abstracts.OrderService;
import com.etiya.northwind.business.requests.carts.CreateRequestAddToOrder;
import com.etiya.northwind.business.requests.carts.CreateRequestCart;
import com.etiya.northwind.business.requests.carts.DeleteRequestCart;
import com.etiya.northwind.business.requests.carts.UpdateRequestCart;
import com.etiya.northwind.business.responses.carts.CartListResponse;
import com.etiya.northwind.business.responses.carts.GetCartResponse;
import com.etiya.northwind.core.utilities.mapping.ModelMapperService;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;
import com.etiya.northwind.core.utilities.results.SuccessDataResult;
import com.etiya.northwind.core.utilities.results.SuccessResult;
import com.etiya.northwind.dataAccess.abstracts.CartRepository;
import com.etiya.northwind.dataAccess.abstracts.OrderDetailRepository;
import com.etiya.northwind.dataAccess.abstracts.OrderRepository;
import com.etiya.northwind.entities.concretes.Cart;
import com.etiya.northwind.entities.concretes.Order;
import com.etiya.northwind.entities.concretes.OrderDetail;

@Service
public class CartManager implements CartService {

	
	private CartRepository cartRepository;
	private ModelMapperService modelMapperService;
	
	private OrderRepository orderRepository;
	private OrderDetailRepository orderDetailRepository;
	
	@Autowired
	public CartManager(CartRepository cartRepository, ModelMapperService modelMapperService, OrderRepository orderRepository, OrderDetailRepository orderDetailRepository ) {
		
		this.cartRepository = cartRepository;
		this.modelMapperService = modelMapperService;
		this.orderRepository = orderRepository ;
		this.orderDetailRepository = orderDetailRepository;
	}

	@Override
	public DataResult<List<CartListResponse>> getAll() {
		List<Cart> result = this.cartRepository.findAll();
		List<CartListResponse> response = result.stream().map(cart->
		this.modelMapperService.forResponse().map(cart, CartListResponse.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CartListResponse>>(response);
	}

	@Override
	public Result add(CreateRequestCart createRequestCart) {
		Cart cart = this.modelMapperService.forRequest().map(createRequestCart,Cart.class);
		this.cartRepository.save(cart);
		return new SuccessResult("Sepet eklendi");
	}

	@Override
	public DataResult<GetCartResponse> getById(String customerId, int productId) {
		Cart cart = this.cartRepository.getByCustomerIdAndProductId(customerId, productId);
		GetCartResponse response = this.modelMapperService.forResponse().map(cart, GetCartResponse.class);
		return new SuccessDataResult<GetCartResponse>(response);
	}

	@Override
	public Result delete(DeleteRequestCart deleteRequestCart) {
		this.cartRepository.deleteCartWithCustomerIdAndProductId(deleteRequestCart.getCustomerId(), deleteRequestCart.getProductId());
		return new SuccessResult("Cart deleted");
	}

	@Override
	public Result update(UpdateRequestCart updateRequestCart) {
		Cart cart = this.modelMapperService.forRequest().map(updateRequestCart, Cart.class);
		this.cartRepository.save(cart);
		return new SuccessResult("Cart updated");
	}
	@Override
	public Result addToOrder(CreateRequestAddToOrder createRequestAddToOrder) {
		Order order = this.modelMapperService.forRequest().
				map(createRequestAddToOrder, Order.class);
		this.orderRepository.save(order);
		addToOrderDetail(createRequestAddToOrder);
		this.cartRepository.deleteCartWithCustomerIdAndProductId(createRequestAddToOrder.getCustomerId(), createRequestAddToOrder.getProductId());

		return new SuccessResult("Order added");
	}
	
	private void addToOrderDetail(CreateRequestAddToOrder createRequestAddToOrder) {
		OrderDetail orderDetail = this.modelMapperService.forRequest().
				map(createRequestAddToOrder, OrderDetail.class);
		this.orderDetailRepository.save(orderDetail);
	
	}



}
