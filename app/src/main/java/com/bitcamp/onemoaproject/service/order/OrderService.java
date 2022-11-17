package com.bitcamp.onemoaproject.service.order;

import com.bitcamp.onemoaproject.vo.order.Order;
import com.bitcamp.onemoaproject.vo.order.OrderStatus;

import java.util.List;

public interface OrderService {

    void add(Order order) throws Exception;

    Order get(int no) throws Exception;

    boolean delete(int no) throws Exception;

    List<Order> salesList(int no) throws Exception;

    List<Order> buysList(int no) throws Exception;

//    boolean update(OrderStatus orderStatus, int orderNo);

    boolean update(int orderStatus, int orderNo);

}
