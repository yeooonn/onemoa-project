package com.bitcamp.onemoaproject.service;

import com.bitcamp.onemoaproject.dao.OrderDao;
import com.bitcamp.onemoaproject.vo.order.Order;
import com.bitcamp.onemoaproject.vo.paging.Criteria;
import com.bitcamp.onemoaproject.vo.product.AttachedFile;
import com.bitcamp.onemoaproject.vo.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface OrderService {

    void add(Order order) throws Exception;

    boolean update(Order order) throws Exception;

    Order get(int no) throws Exception;

    boolean delete(int no) throws Exception;

    List<Order> salesList(int no) throws Exception;

    List<Order> buysList(int no) throws Exception;

}
