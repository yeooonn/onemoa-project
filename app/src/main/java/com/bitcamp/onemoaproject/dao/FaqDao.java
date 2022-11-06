package com.bitcamp.onemoaproject.dao;

import com.bitcamp.onemoaproject.vo.Faq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FaqDao {

     int insert(Faq faq);
     Faq findByNo(int no);

     int update(Faq faq);

     int delete(int no);
     List<Faq> findAll();

     //  int deleteByMember(int memberNo);
}














