package com.bitcamp.onemoaproject.dao;



import com.bitcamp.onemoaproject.vo.product.ProductCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductCategoryDao {

    //    int insert(ProductC product);
//
//    Product findByNo(int no);
//
//    int update(Product product);
//
//    int delete(int no);
//
//    //  int deleteByMember(int memberNo);
//
    List<ProductCategory> findAll();
//
}
