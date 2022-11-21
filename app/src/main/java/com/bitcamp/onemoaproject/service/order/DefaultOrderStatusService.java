package com.bitcamp.onemoaproject.service.order;


import com.bitcamp.onemoaproject.dao.order.OrderDao;
import com.bitcamp.onemoaproject.dao.order.OrderStatusDao;
import com.bitcamp.onemoaproject.vo.order.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultOrderStatusService implements OrderStatusService {

  @Autowired
  OrderDao orderDao;

  @Autowired
  OrderStatusDao orderStatusDao;


//  public void add(Order order) throws Exception {
//    if (orderDao.insert(order) == 0) {
//      throw new Exception("주문건 등록 실패!");
//    }
//  }
//
//  public boolean update(Order order) throws Exception {
//    return orderDao.update(order) != 0;
//  }
//
//  public Order get(int no) throws Exception {
//    return orderDao.findByNo(no);
//  }
//
//  public boolean delete(int no) throws Exception {
//    return orderDao.delete(no) > 0;
//  }
  public OrderStatus get(int no) throws Exception {
      return orderStatusDao.findByNo(no);
  }

  public List<OrderStatus> list() throws Exception {
    return orderStatusDao.findAll();
  }
}
