package com.bitcamp.onemoaproject.dao;

import com.bitcamp.onemoaproject.vo.product.Product;
import com.bitcamp.onemoaproject.vo.product.ProductReview;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductReviewDao {

  int count(int no);

  double getAverage(int no);

  List<ProductReview> findAll();

  List<ProductReview> findByProductNo(int no);
}














