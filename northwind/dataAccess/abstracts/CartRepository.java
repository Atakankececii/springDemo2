package com.etiya.northwind.dataAccess.abstracts;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.etiya.northwind.entities.concretes.Cart;
import com.etiya.northwind.entities.concretes.CartsPK;

public interface CartRepository extends JpaRepository<Cart, CartsPK> {
	
	Cart getByCustomerIdAndProductId(String customerId,int productId);


	
	@Transactional
	@Modifying
	@Query("Delete from Cart Where customer_id=:customerId and product_id=:productId")	
void deleteCartWithCustomerIdAndProductId(@Param("customerId")String customerId,@Param("productId")int productId);
}
