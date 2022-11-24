package com.bitcamp.onemoaproject.service.productService;

import com.bitcamp.onemoaproject.dao.WishDao;
import com.bitcamp.onemoaproject.dao.product.ProductDao;
import com.bitcamp.onemoaproject.dao.product.ProductWishDao;
import com.bitcamp.onemoaproject.vo.product.ProductWish;
import java.util.List;
import java.util.Map;

import com.bitcamp.onemoaproject.vo.paging.Criteria;
import com.bitcamp.onemoaproject.vo.product.AttachedFile;
import com.bitcamp.onemoaproject.vo.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class DefaultProductService implements ProductService {

  @Autowired
  ProductDao productDao;
  
  @Autowired
  ProductWishDao productWishDao;
  
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
  public boolean invalid(int no) {
    if (productDao.makeinvalid(no) == 0) {

      return false;
    }
    return true;
  }

  @Override
  public boolean valid(int no) {
    if (productDao.makevalid(no) == 0) {

      return false;
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
  public List<Product> list(String code) throws Exception {
    return productDao.findByCategory(code);
  }

  @Override
  public List<Product> list(int no) throws Exception {
    return productDao.findByWriter(no);
  }

  @Override
  public List<Product> listByAdmin() throws Exception {
    return productDao.findAllByAdmin();
  }

  @Override
  public List<Map<String, Object>> selectProductList(Criteria cri) {
    return productDao.selectProductList(cri);
  }

  @Override
  public int countProductListTotal(String code) {
    return productDao.countProductList(code);
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
  public List<ProductWish> findByAll() throws Exception {
    return productWishDao.findByAllCount();
  }
  
  //  public double getReviewAvg(int productNo) throws Exception {
//    return productDao.getReviewAvg(productNo);
//  }

}
