package com.bitcamp.onemoaproject.dao;



import java.util.List;

public class ProductCategoryDao {
    int insert(ProductC product);

    Product findByNo(int no);

    int update(Product product);

    int delete(int no);

    //  int deleteByMember(int memberNo);

    List<Product> findAll();
}
