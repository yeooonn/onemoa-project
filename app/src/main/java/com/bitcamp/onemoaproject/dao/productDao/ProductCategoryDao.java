package com.bitcamp.onemoaproject.dao.productDao;



import com.bitcamp.onemoaproject.vo.product.ProductCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

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

    List<Map> findByParent(String code);

    String findByChild(String code);

    String findNameByCode(String code);

//
}
