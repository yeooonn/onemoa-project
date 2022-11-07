package com.bitcamp.onemoaproject.service.productService;

import com.bitcamp.onemoaproject.dao.productDao.ProductCategoryDao;
import com.bitcamp.onemoaproject.vo.product.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DefaultProductCategoryService implements ProductCategoryService {

  @Autowired
  ProductCategoryDao productCategoryDao;


  @Override
  public List<ProductCategory> list() {
    return productCategoryDao.findAll();
  }

  @Override
  public List<Map> getSubCategories(String code) { return productCategoryDao.findByParent(code); }

}
