package com.bitcamp.onemoaproject.service.order;

import com.bitcamp.onemoaproject.dao.order.OrderReviewDao;
import com.bitcamp.onemoaproject.vo.order.OrderReview;
import com.bitcamp.onemoaproject.vo.order.OrderReviewAttachedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DefaultOrderReviewService implements OrderReviewService {

  @Autowired
  OrderReviewDao orderReviewDao;

  @Transactional
  @Override
  public void reviewAdd(OrderReview orderReview) throws Exception {

    // 1) 게시글
    if (orderReviewDao.insert(orderReview) == 0) {
      throw new Exception("게시글 등록 실패!");
    }

    // 2) 첨부파일 등록
    if (orderReview.getOrderReviewAttachedFiles().size() > 0) {
      orderReviewDao.insertFiles(orderReview);
    }
  }
  
  @Override
  public void reviewAdd1(OrderReview orderReview) throws Exception {
    
    // 1) 게시글
    if (orderReviewDao.insert(orderReview) == 0) {
      throw new Exception("게시글 등록 실패!");
    }
  }

  @Transactional
  @Override
  public boolean reviewUpdate(OrderReview orderReview) throws Exception {
    // 1) 게시글 변경
    if (orderReviewDao.OrderReviewUpdate(orderReview) == 0) {
      return false;
    }

    // 2) 첨부파일 추가
    if (orderReview.getOrderReviewAttachedFiles().size() > 0) {
      orderReviewDao.insertFiles(orderReview);
    }
    return true;
  }

  @Override
  public OrderReview get(int no) throws Exception {
    return orderReviewDao.findByNo(no);
  }

  public OrderReview get2(int no) throws Exception {
    return orderReviewDao.findByNo2(no);
  }

  @Override
  public List<OrderReview> list() throws Exception {
    return orderReviewDao.findAll();
  }

  @Override
  public List<OrderReview> list(int no) throws Exception {
    return orderReviewDao.findAll(no);
  }

  @Override
  public List<OrderReview> listByPno(int no) throws Exception {
    return orderReviewDao.findAllByProduct(no);
  }


  @Override
  public OrderReviewAttachedFile getAttachedFile(int fileNo) throws Exception {
    return orderReviewDao.findFileByNo(fileNo);
  }

  @Override
  public boolean deleteAttachedFile(int fileNo) throws Exception {
    return orderReviewDao.deleteFile(fileNo) > 0;
  }

  @Override
  public int count(int no) {
    return orderReviewDao.countReviews(no);
  }

  @Override
  public double getReviewAverage(int no) {
    System.out.println(orderReviewDao.getAverage(no));
    return orderReviewDao.getAverage(no);
  }
  
  @Override
  public List<OrderReview> listMainReview() {
    return orderReviewDao.findAllByMainReview();
  }
}








