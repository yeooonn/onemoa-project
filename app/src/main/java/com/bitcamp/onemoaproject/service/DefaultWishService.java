package com.bitcamp.onemoaproject.service;

import com.bitcamp.onemoaproject.dao.WishDao;
import com.bitcamp.onemoaproject.vo.Member;
import com.bitcamp.onemoaproject.vo.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultWishService {

  @Autowired
  WishDao wishDao;

  public void add(Member member, int no) throws Exception {

    if (wishDao.insert(member, no) == 0) {
      throw new Exception("위시실패!");
    }

  }

  public int get(Member member, Product product) throws Exception {
    if (wishDao.find(member, product) == null) {
      return 0;
    }
    return 1;
  }

  public int getCount(int no) throws Exception {
    return wishDao.findByProductNo(no);
  }


  public boolean delete(Member member, Product product) throws Exception {
    return wishDao.delete(member, product) > 0;
  }

}
