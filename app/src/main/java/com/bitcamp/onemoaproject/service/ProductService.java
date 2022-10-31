package com.bitcamp.onemoaproject.service;

import java.util.List;
import com.bitcamp.onemoaproject.vo.Product;

public interface ProductService {

  //  void add(Product product) throws Exception;
  //
  //  boolean update(Product product) throws Exception;
  //
  //  Product get(int no) throws Exception;
  //
  //  boolean delete(int no) throws Exception;

  List<Product> list() throws Exception;

  //  AttachedFile getAttachedFile(int fileNo) throws Exception;

  //  boolean deleteAttachedFile(int fileNo) throws Exception;
}
