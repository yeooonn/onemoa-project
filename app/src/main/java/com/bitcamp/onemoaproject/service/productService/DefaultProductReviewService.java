package com.bitcamp.onemoaproject.service.productService;

import com.bitcamp.onemoaproject.dao.productDao.ProductReviewDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultProductReviewService implements ProductReviewService {

  @Autowired
  ProductReviewDao productReviewDao;

  @Override
  public int count(int no) {
    return productReviewDao.count(no);
  }

  @Override
  public double getReviewAverage(int no) {
    System.out.println(productReviewDao.getAverage(no));
    return productReviewDao.getAverage(no);
  }
}
