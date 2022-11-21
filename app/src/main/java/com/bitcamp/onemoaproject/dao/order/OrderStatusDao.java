package com.bitcamp.onemoaproject.dao.order;

import com.bitcamp.onemoaproject.vo.order.OrderStatus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderStatusDao {

//  int insert(Order oder); // 주문 등록
//
//  Order findByNo(int no); // 주문 번호
//
//  int update(Order order); // 주문 업데이트
//
//  int delete(int no); // 주문 취소

  OrderStatus findByNo(int no);

  List<OrderStatus> findAll(); // 주문상태 전체 뽑아오기


}














