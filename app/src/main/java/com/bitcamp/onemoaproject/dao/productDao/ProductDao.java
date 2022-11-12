package com.bitcamp.onemoaproject.dao.productDao;

import java.util.List;
import java.util.Map;

import com.bitcamp.onemoaproject.vo.paging.Criteria;
import com.bitcamp.onemoaproject.vo.product.AttachedFile;
import com.bitcamp.onemoaproject.vo.product.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductDao {

  int insert(Product product); // 재능판매 게시물 입력

  Product findByNo(int no); // 재능판매 게시물 번호로 찾기

  int update(Product product);

  int delete(int no);

  int deleteByMember(int memberNo);

  List<Product> findByCategory(String code);

  int insertFiles(Product product);

  AttachedFile findFileByNo(int fileNo);

  List<AttachedFile> findFilesByProduct(int ProductNo);

  int deleteFile(int fileNo);

  int deleteFiles(int ProductNo);

  int deleteFilesByMemberProducts(int memberNo);

  List<Product> findCategorized(String code);

  // 페이징에 필요한 메소드
  public List<Map<String, Object>> selectProductList(Criteria cri) ;

  // 게시판 글의 개수를 세기위한 메소드
  public int countProductList(String code);

//  double getReviewAvg(int productNo);

}














