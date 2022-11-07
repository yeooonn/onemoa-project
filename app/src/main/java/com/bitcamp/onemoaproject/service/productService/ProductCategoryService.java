package com.bitcamp.onemoaproject.service.productService;

import com.bitcamp.onemoaproject.vo.product.ProductCategory;

import java.util.List;
import java.util.Map;

public interface ProductCategoryService {

    List<ProductCategory> list();

    List<Map> getSubCategories(String code);

}
