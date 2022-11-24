package com.bitcamp.onemoaproject.dao;

import com.bitcamp.onemoaproject.vo.Faq;
import com.bitcamp.onemoaproject.vo.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FaqDao {

     int insert(Faq faq);

     Faq findByNo(int no);

     int update(Faq faq);

     int delete(int no);

     List<Faq> findAll();

     List<Faq> findAll(String type);

     // FAQ 페이징
     List<Map<String, Object>> findAllList(Criteria cri);

     // FAQ 글의 개수 조회
     int findAllCount();
}














