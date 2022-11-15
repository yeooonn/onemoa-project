package com.bitcamp.onemoaproject.service;

import com.bitcamp.onemoaproject.dao.OrderDao;
import com.bitcamp.onemoaproject.dao.productDao.ProductDao;
import com.bitcamp.onemoaproject.service.productService.ProductService;
import com.bitcamp.onemoaproject.vo.order.Order;
import com.bitcamp.onemoaproject.vo.paging.Criteria;
import com.bitcamp.onemoaproject.vo.product.AttachedFile;
import com.bitcamp.onemoaproject.vo.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class DefaultOrderService {

  @Autowired
  OrderDao orderDao;

  public void add(Order order) throws Exception {
    if (orderDao.insert(order) == 0) {
      throw new Exception("주문건 등록 실패!");
    }
  }

  public boolean update(Order order) throws Exception {

    if (orderDao.update(order) == 0) {
      return false;
    }
      return true;
  }

  public Order get(int no) throws Exception {
    return orderDao.findByNo(no);
  }

  public boolean delete(int no) throws Exception {
    return orderDao.delete(no) > 0;
  }


//  public List<Order> list() throws Exception {
//    return orderDao.list();
//  }

}
