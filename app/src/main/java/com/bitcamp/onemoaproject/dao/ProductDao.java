package com.bitcamp.onemoaproject.dao;

import java.util.List;
import com.bitcamp.onemoaproject.vo.product.AttachedFile;
import com.bitcamp.onemoaproject.vo.product.Product;
import org.apache.ibatis.annotations.Mapper;
import com.bitcamp.onemoaproject.vo.product.Product;

@Mapper
public interface ProductDao {

  int insert(Product product); // 재능판매 게시물 입력

  Product findByNo(int no); // 재능판매 게시물 번호로 찾기

  int update(Product product);

  int delete(int no);

  int deleteByMember(int memberNo);

  List<Product> findAll();

  List<Product> findByCategory(String code);

  int insertFiles(Product product);

  AttachedFile findFileByNo(int fileNo);

  List<AttachedFile> findFilesByProduct(int ProductNo);

  int deleteFile(int fileNo);

  int deleteFiles(int ProductNo);

  int deleteFilesByMemberProducts(int memberNo);

  List<Product> findCategorized(String code);

//  double getReviewAvg(int productNo);

}














