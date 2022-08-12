package com.etiya.northwind.business.abstracts;

import java.util.List;

import com.etiya.northwind.business.requests.carts.CreateRequestAddToOrder;
import com.etiya.northwind.business.requests.carts.CreateRequestCart;
import com.etiya.northwind.business.requests.carts.DeleteRequestCart;
import com.etiya.northwind.business.requests.carts.UpdateRequestCart;
import com.etiya.northwind.business.responses.carts.CartListResponse;
import com.etiya.northwind.business.responses.carts.GetCartResponse;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;

public interface CartService {

	DataResult<List<CartListResponse>> getAll();
	
	Result add(CreateRequestCart createRequestCart) ;
	DataResult<GetCartResponse> getById(String customerId, int productId);
	Result delete(DeleteRequestCart deleteRequestCart);
	Result update(UpdateRequestCart updateRequestCart);
	Result addToOrder(CreateRequestAddToOrder createRequestAddToOrder);
}
