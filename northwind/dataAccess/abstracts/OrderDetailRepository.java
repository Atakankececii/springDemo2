package com.etiya.northwind.dataAccess.abstracts;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.etiya.northwind.entities.concretes.OrderDetail;
import com.etiya.northwind.entities.concretes.OrderDetailsPK;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailsPK> {

OrderDetail getByOrderIdAndProductId( int orderId,int productId);
	
	@Transactional
	@Modifying
	@Query("Delete From OrderDetail  Where order_id =:orderId and product_id =:productId")
	void deleteOrderDetailWithOrderIdAndProductId( @Param("orderId")int orderId,@Param("productId")int productId);
	
}


