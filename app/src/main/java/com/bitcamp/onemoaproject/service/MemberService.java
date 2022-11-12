package com.bitcamp.onemoaproject.service;

import com.bitcamp.onemoaproject.vo.Member;
import java.util.List;

import java.util.List;

// 비즈니스 로직을 수행하는 객체의 사용규칙(호출규칙)
public interface MemberService {
  
  // 회원 등록
  void add(Member member) throws Exception;
  
  // 회원 이름과 이메일 검색
  Member getIdEmail(String name, String email) throws Exception;
  
  // 회원 패스워드 초기화
  boolean updatePwd(Member member) throws Exception;
  
  boolean update(Member member) throws Exception;
  
  Member get(int no) throws Exception;
  
  Member get(String email, String password) throws Exception;
  
  Member get(String email) throws Exception;
  
  Member getNickName(String nickname) throws Exception;
  
  boolean delete(int no) throws Exception;
  
  List<Member> list() throws Exception;
  
  List<Member> getFieldMemberPortfolio(int fieldMemberNo) throws Exception;
}
