package com.bitcamp.onemoaproject.service.order;

import com.bitcamp.onemoaproject.vo.order.OrderReview;
import com.bitcamp.onemoaproject.vo.order.OrderReviewAttachedFile;

import java.util.List;

public interface OrderReviewService {

// 비즈니스 로직을 수행하는 객체의 사용규칙(호출규칙)
    void reviewAdd(OrderReview orderReview) throws Exception;
    void reviewAdd1(OrderReview orderReview) throws Exception;
    boolean reviewUpdate(OrderReview orderReview) throws Exception;
    OrderReview get(int no) throws Exception;
    List<OrderReview> list() throws Exception;
    List<OrderReview> list(int no) throws Exception;
    List<OrderReview> listByPno(int no) throws Exception;
    OrderReviewAttachedFile getAttachedFile(int fileNo) throws Exception;
    boolean deleteAttachedFile(int fileNo) throws Exception;

    int count(int no);

    double getReviewAverage(int no);
    
    // 메인페이지 후기 목록 : 1121추가
    List<OrderReview> listMainReview();

}

//    boolean delete(int no) throws Exception;









