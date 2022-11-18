package com.bitcamp.onemoaproject.service.productService;


import com.bitcamp.onemoaproject.dao.productDao.ProductReviewDao;
import com.bitcamp.onemoaproject.vo.product.ProductReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

  @Override
  public List<ProductReview> list() {
    return productReviewDao.findAll();
  }

  public List<ProductReview> list(int no) {
    return productReviewDao.findByProductNo(no);
  }
  
  @Override
  public List<ProductReview> listMainReview() {
    return productReviewDao.findAllByMainReview();
  }
}
