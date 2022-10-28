package com.bitcamp.onemoaproject.service;

import java.util.List;
import com.bitcamp.onemoaproject.vo.Member;

// 비즈니스 로직을 수행하는 객체의 사용규칙(호출규칙)
//
public interface MemberService {

  void add(Member member) throws Exception;

  boolean update(Member member) throws Exception;

  Member get(int no) throws Exception;

  Member get(String email, String password) throws Exception;

  Member get(String email) throws Exception;

  Member getNickName(String nickname) throws Exception;

  //boolean delete(int no) throws Exception;

  List<Member> list() throws Exception;
}


