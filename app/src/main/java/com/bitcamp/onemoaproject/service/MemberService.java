package com.bitcamp.onemoaproject.service;

import java.util.List;
import com.bitcamp.onemoaproject.vo.Member;

// 비즈니스 로직을 수행하는 객체의 사용규칙(호출규칙)
//
public interface MemberService {

  void add(Member member) throws Exception;

  boolean update(Member member) throws Exception;

  int updateStatus(String email) throws Exception;

  boolean myinfoUpdate(Member member) throws Exception;

  boolean myinfoUpdate2(Member member) throws Exception;

  Member get(int no) throws Exception;

  Member get(String email, String password) throws Exception;

  Member get(String email) throws Exception;

  boolean delete(int no) throws Exception;

  List<Member> list() throws Exception;

  List<Member> getFieldMemberPortfolio(int fieldMemberNo) throws Exception;

  void modifyPasswd(String email, String newPassword);

  Member getNickName(String nickname);

  Member getIdEmail(String name, String email);

  boolean updatePwd(Member member);
  
  // 사용자 패스워드 체크
  int getPasswordCheck(String password, int mno);
}








