package com.abubakar.service;

import java.util.List;
import java.util.Set;

import com.abubakar.domain.OrderStatus;
import com.abubakar.model.Address;
import com.abubakar.model.Cart;
import com.abubakar.model.Order;
import com.abubakar.model.OrderItem;
import com.abubakar.model.User;

public interface OrderService {
	Set<Order> createOrder(User user, Address shippingAddress, Cart cart);
	Order findOrderById(Long id) throws Exception;
	List<Order> userOrderHistory(Long userId);
	List<Order> sellersOrder(Long sellerId);
	Order updateOrderStatus(Long orderId, OrderStatus orderStatus) throws Exception;
	Order cancelOrder(Long orderId, User user) throws Exception;
	OrderItem getOrderItemById(Long id) throws Exception;
}
 