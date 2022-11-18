package com.bitcamp.onemoaproject.dao;

import com.bitcamp.onemoaproject.vo.order.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewDao {

//
//  int insert(Review review); // 리뷰 등록
//
//  Order findByNo(int no); // 주문 번호
//
//  int update(Order order); // 주문 업데이트
//
//  int delete(int no); // 주문 취소
//
//  List<Order> findAll(); // 주문 목록 전체 뽑기

  // 판매자의 회원번호로 판매내역 조회
  List<Order> findBySellerNo(int no);

  // 구매자의 회원번호로 구매내역 조회
  List<Order> findByBuyerNo(int no);

//  int updateStatus(OrderStatus orderStatus, int orderNo);

//  int updateStatus(int orderStatus, int orderNo);

}














