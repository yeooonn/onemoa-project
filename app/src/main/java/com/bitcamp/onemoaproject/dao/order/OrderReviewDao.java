package com.bitcamp.onemoaproject.dao.order;


import com.bitcamp.onemoaproject.vo.order.OrderReview;
import com.bitcamp.onemoaproject.vo.order.OrderReviewAttachedFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderReviewDao {

    int insert(OrderReview orderReview);

    OrderReview findByNo(int no);

    int OrderReviewUpdate(OrderReview orderReview);

    List<OrderReview> findAll();

    List<OrderReview> findAll(int no);

    List<OrderReview> findAllByProduct(int no);

    int insertFiles(OrderReview orderReview);

    OrderReviewAttachedFile findFileByNo(int fileNo);

    List<OrderReviewAttachedFile> findFilesByReview(int ReviewNo);

    int deleteFile(int fileNo);

    int deleteFiles(int No);

    OrderReview findByNo2(int no);

    int countReviews(int no);

    double getAverage(int no);
    
    // 메인페이지 후기 목록 : 1121추가
    List<OrderReview> findAllByMainReview();
}














