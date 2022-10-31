package com.bitcamp.onemoaproject.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.bitcamp.onemoaproject.vo.Product;

@Mapper
public interface ProductDao {

  int insert(Product product);

  Product findByNo(int no);

  int update(Product product);

  int delete(int no);

  //  int deleteByMember(int memberNo);

  List<Product> findAll();

  int insertFiles(Product product);

  //  AttachedFile findFileByNo(int fileNo);
  //
  //  List<AttachedFile> findFilesByProduct(int ProductNo);
  //
  //  int deleteFile(int fileNo);
  //
  //  int deleteFiles(int ProductNo);
  //
  //  int deleteFilesByMemberProducts(int memberNo);

}














