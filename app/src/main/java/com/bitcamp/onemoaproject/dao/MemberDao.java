package com.bitcamp.onemoaproject.dao;

import com.bitcamp.onemoaproject.vo.Member;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberDao {
  int insert(Member member);
  
  Member findByNo(int no);
  
  int update(Member member);
  
  int delete(int no);
  
  List<Member> findAll();
  
  //  Member findByEmailPassword(String email, String password);
  // 메서드의 파라미터가 여러 개일 때,
  // - SQL에서 참조할 파라미터라고 애노테이션으로 표시해야한다.
  Member findByEmailPassword(@Param("email") String email, @Param("password") String password);
  
  Member findByEmail(String email);
  
  Member findByNickName(String nickname);
}