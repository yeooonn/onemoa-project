package com.bitcamp.onemoaproject.service.productService;

import com.bitcamp.onemoaproject.vo.product.ProductWish;
import java.util.List;
import java.util.Map;

import com.bitcamp.onemoaproject.vo.paging.Criteria;
import com.bitcamp.onemoaproject.vo.product.AttachedFile;
import com.bitcamp.onemoaproject.vo.product.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {

    void add(Product product) throws Exception;

    boolean update(Product product) throws Exception;

    boolean invalid(int no) throws Exception;

    boolean valid(int no);

    Product get(int no) throws Exception;

    boolean delete(int no) throws Exception;

    List<Product> list(String code) throws Exception;

    List<Product> list(int no) throws Exception;

    List<Product> listByAdmin() throws Exception;

    List<Map<String, Object>> selectProductList(Criteria cri);

    int countProductListTotal(String code);

    AttachedFile getAttachedFile(int fileNo) throws Exception;

    boolean deleteAttachedFile(int fileNo) throws Exception;
    
    List<ProductWish> findByAll() throws Exception;
//    double getReviewAvg(int productNo) throws Exception;
}
