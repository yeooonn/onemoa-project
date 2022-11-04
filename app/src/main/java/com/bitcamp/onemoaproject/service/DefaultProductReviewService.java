package com.bitcamp.onemoaproject.service;

import com.bitcamp.onemoaproject.dao.ProductDao;
import com.bitcamp.onemoaproject.dao.ProductReviewDao;
import com.bitcamp.onemoaproject.vo.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultProductReviewService implements ProductReviewService {

  @Autowired
  ProductReviewDao productReviewDao;

  @Override
  public double get(int no) {
    System.out.println(productReviewDao.getAvg(no));
    return productReviewDao.getAvg(no);
  }
}
