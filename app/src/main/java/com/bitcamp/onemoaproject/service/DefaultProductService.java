package com.bitcamp.onemoaproject.service;

import java.util.List;
import com.bitcamp.onemoaproject.vo.product.AttachedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bitcamp.onemoaproject.dao.ProductDao;
import com.bitcamp.onemoaproject.vo.product.Product;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultProductService implements ProductService {

  @Autowired
  ProductDao productDao;
  private String categoryCode;

  @Override
  public void add(Product product) throws Exception {

    if (productDao.insert(product) == 0) {
      throw new Exception("게시글 등록 실패!");
    }

    if (product.getAttachedFiles().size() > 0) {
      productDao.insertFiles(product);
    }
  }

  @Transactional
  @Override
  public boolean update(Product product) throws Exception {

    if (productDao.update(product) == 0) {
      return false;
    }

    if (product.getAttachedFiles().size() > 0) {
      productDao.insertFiles(product);
    }
      return true;
  }

  @Override
  public Product get(int no) throws Exception {
    return productDao.findByNo(no);
  }

  @Transactional
  @Override
  public boolean delete(int no) throws Exception {

    productDao.deleteFiles(no);

    return productDao.delete(no) > 0;
  }

  @Override
  public List<Product> list() throws Exception {
    return productDao.findAll();
  }


  @Override
  public AttachedFile getAttachedFile(int fileNo) throws Exception {
    return productDao.findFileByNo(fileNo);
  }

  @Override
  public boolean deleteAttachedFile(int fileNo) throws Exception {
    return productDao.deleteFile(fileNo) > 0;
  }

  @Override
  public List<Product> list(String code) throws Exception {
    return productDao.findByCategory(code);
  }

//  public double getReviewAvg(int productNo) throws Exception {
//    return productDao.getReviewAvg(productNo);
//  }

}
