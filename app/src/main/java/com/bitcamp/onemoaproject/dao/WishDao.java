package com.bitcamp.onemoaproject.dao;

import com.bitcamp.onemoaproject.vo.Member;
import com.bitcamp.onemoaproject.vo.Wish;
import com.bitcamp.onemoaproject.vo.product.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WishDao {

    int insert(Member member, int no);
    Wish find(Member member, Product product);
    int findByProductNo(int no);
    List<Wish> findWishsByProductNo(int no);
    int delete(Member member, Product product);
}
