package com.bitcamp.onemoaproject.service;

import com.bitcamp.onemoaproject.vo.product.ProductReview;

import java.util.List;

public interface ProductReviewService {

    int count(int no);

    double getReviewAverage(int no);

    List<ProductReview> list(int no);

    List<ProductReview> list();
}
