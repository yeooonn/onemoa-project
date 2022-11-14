package com.bitcamp.onemoaproject.dao;

import com.bitcamp.onemoaproject.vo.order.Order;
import com.bitcamp.onemoaproject.vo.product.AttachedFile;
import com.bitcamp.onemoaproject.vo.product.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDao {

  int insert(Order oder); // 주문 등록
  Order findByNo(int no); // 주문 번호
  int update(Order order); // 주문 업데이트
  int delete(int no); // 주문 취소
  List<Order> findAll(); // 주문 목록 뽑기

}














