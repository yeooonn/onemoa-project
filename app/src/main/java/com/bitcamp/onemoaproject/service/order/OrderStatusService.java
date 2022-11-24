package com.bitcamp.onemoaproject.service.order;

import com.bitcamp.onemoaproject.vo.Faq;
import com.bitcamp.onemoaproject.vo.order.Order;
import com.bitcamp.onemoaproject.vo.order.OrderStatus;

import java.util.List;

public interface OrderStatusService {

//    void add(Order order) throws Exception;
//
//    boolean update(Order order) throws Exception;
//
//    Order get(int no) throws Exception;
//
//    boolean delete(int no) throws Exception;

    OrderStatus get(int no) throws Exception;

    List<OrderStatus> list() throws Exception;
}
