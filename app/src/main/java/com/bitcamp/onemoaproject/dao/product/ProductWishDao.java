package com.bitcamp.onemoaproject.dao.product;

import com.bitcamp.onemoaproject.vo.product.ProductWish;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductWishDao {
    List<ProductWish> findByAllCount();
}
