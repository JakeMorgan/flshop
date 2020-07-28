package com.accenture.be.business;

import com.accenture.be.access.OrderAccessService;
import com.accenture.be.entity.Order;
import com.accenture.be.entity.User;
import com.accenture.fe.Enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class OrderBusinessServiceImpl implements OrderBusinessService {
    @Autowired
    private OrderAccessService orderAccessService;


    @Override
    public Order createOrder(User user, BigDecimal total) {
        Order order = new Order(user, total);
        orderAccessService.create(order);
        return order;
    }

    @Override
    public List<Order> ordersList() {
        return orderAccessService.getOrders();
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return orderAccessService.getOrderByUser(user);
    }

    public void completeOrder(Long id){
        Order order = orderAccessService.getById(id);
        order.setStatus(OrderStatus.Complited);
        orderAccessService.update(order);
    }
}
