package com.bitcamp.onemoaproject.service.order;

import com.bitcamp.onemoaproject.vo.order.OrderReview;
import com.bitcamp.onemoaproject.vo.order.OrderReviewAttachedFile;
import com.bitcamp.onemoaproject.vo.paging.Criteria;
import com.bitcamp.onemoaproject.vo.product.ProductReview;

import javax.servlet.ServletResponse;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface OrderReviewService {

// 비즈니스 로직을 수행하는 객체의 사용규칙(호출규칙)
    void reviewAdd(OrderReview orderReview) throws Exception;
    boolean reviewUpdate(OrderReview orderReview) throws Exception;
    OrderReview get(int no) throws Exception;
    List<OrderReview> list() throws Exception;
    List<OrderReview> list(int no) throws Exception;
    List<OrderReview> listByPno(int no) throws Exception;
    OrderReviewAttachedFile getAttachedFile(int fileNo) throws Exception;
    boolean deleteAttachedFile(int fileNo) throws Exception;

    int count(int no);

    double getReviewAverage(int no);

}

//    boolean delete(int no) throws Exception;









