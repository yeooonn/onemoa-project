package com.bitcamp.onemoaproject.service.order;

import com.bitcamp.onemoaproject.dao.orderDao.OrderDao;
import com.bitcamp.onemoaproject.vo.order.Order;
import com.bitcamp.onemoaproject.vo.order.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultOrderService implements OrderService {


  @Autowired
  OrderDao orderDao;


  public void add(Order order) throws Exception {
    if (orderDao.insert(order) == 0) {
      throw new Exception("주문건 등록 실패!");
    }
  }


//  public boolean update(OrderStatus orderStatus, int orderNo) {
//    return orderDao.updateStatus(orderStatus, orderNo) != 0;
//  }

  public boolean update(int orderStatus, int orderNo) {
    return orderDao.updateStatus(orderStatus, orderNo) != 0;
  }


  public Order get(int no) throws Exception {
    return orderDao.findByNo(no);
  }

  public boolean delete(int no) throws Exception {
    return orderDao.delete(no) > 0;
  }

  public List<Order> list() throws Exception {
    return orderDao.findAll();
  }

  public List<Order> salesList(int no) throws Exception {
    return orderDao.findBySellerNo(no);
  }

  public List<Order> buysList(int no) throws Exception {
    return orderDao.findByBuyerNo(no);
  }


//
//  public List<Order> list() throws Exception {
//    return orderDao.list();
//  }

}
